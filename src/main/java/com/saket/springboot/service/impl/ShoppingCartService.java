package com.saket.springboot.service.impl;

import com.saket.springboot.domain.Item;
import com.saket.springboot.domain.Product;
import com.saket.springboot.domain.ShoppingCart;
import com.saket.springboot.domain.User;
import com.saket.springboot.dto.ShoppingCartDTO;
import com.saket.springboot.exception.CartAlreadyClosedException;
import com.saket.springboot.exception.ItemOutOfStockException;
import com.saket.springboot.exception.ShoppingCartNotFoundException;
import com.saket.springboot.model.ShoppingCartStatus;
import com.saket.springboot.repository.IShoppingCartRepository;
import com.saket.springboot.service.IProductService;
import com.saket.springboot.service.IShoppingCartService;
import com.saket.springboot.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional(Transactional.TxType.REQUIRED)
public class ShoppingCartService implements IShoppingCartService {

    private final IProductService productService;
    private final IUserService userService;
    private final IShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartService(IProductService productService, IUserService userService, IShoppingCartRepository IShoppingCartRepository) {
        this.productService = productService;
        this.userService = userService;
        this.shoppingCartRepository = IShoppingCartRepository;
    }

    @Override
    public ShoppingCart addItemsInCart(ShoppingCartDTO shoppingCartDTO) {


        Product product = productService.getProductByProductId(shoppingCartDTO.getProductId());
        User user = userService.getUserByUserId(shoppingCartDTO.getUserId());
        ShoppingCart shoppingCart = null;
        List<ShoppingCart> activeShoppingCart = shoppingCartRepository.findByUserAndStatus(user, ShoppingCartStatus.ACTIVE);
        if (CollectionUtils.isEmpty(activeShoppingCart)) {
            shoppingCart = new ShoppingCart(user);
        } else {
            shoppingCart = activeShoppingCart.get(0);
        }
        throwIfItemIsOutOfStock(product, shoppingCartDTO.getStock());
        shoppingCart.addItem(new Item(product, shoppingCartDTO.getStock()));

        log.debug("Shopping object: " + shoppingCart);
        return shoppingCartRepository.save(shoppingCart);
    }

    private void throwIfItemIsOutOfStock(Product product, Integer stock) {
        if (product.getQuantity() < stock) {
            log.error("Item is out of stock: " + product.getName());
            throw new ItemOutOfStockException("Item out of stock", product.getId(), product.getQuantity(), stock);
        }
    }

    @Override
    public List<ShoppingCart> getShoppingCarts(ShoppingCartStatus shoppingCartStatus) {
        log.debug("Shopping cart status: " + shoppingCartStatus);
        return shoppingCartRepository.findByStatus(shoppingCartStatus);
    }

    @Override
    public ShoppingCart updateProduct(ShoppingCartDTO shoppingCartDTO, Long cartId) {
        ShoppingCart shoppingCartToBeUpdated = shoppingCartRepository.findOne(cartId);
        if (shoppingCartToBeUpdated == null || shoppingCartToBeUpdated.getStatus() != ShoppingCartStatus.ACTIVE) {
            log.error("Active Cart not Found" + shoppingCartDTO.getUserId());
            throw new ShoppingCartNotFoundException("Cart not found", cartId);
        }
        shoppingCartToBeUpdated.addItem(
                new Item(
                        productService.getProductByProductId(shoppingCartDTO.getProductId()), shoppingCartDTO.getStock()
                ));

        return shoppingCartRepository.save(shoppingCartToBeUpdated);
    }

    @Override
    public void deleteShoppingCart(Long id) {
        log.debug("Deleting Cart: " + id);
        ShoppingCart shoppingCart = shoppingCartRepository.findOne(id);
        if(shoppingCart == null){
            throw new ShoppingCartNotFoundException("Shopping cart not found", id);
        }
        shoppingCartRepository.delete(id);
    }

    @Override
    public void clearAllShoppingCarts() {
        shoppingCartRepository.delete(getShoppingCarts(ShoppingCartStatus.ACTIVE));
    }

    @Override
    public void purchaseShoppingCart(Long cartId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findOne(cartId);
        if (shoppingCart == null || shoppingCart.getStatus() != ShoppingCartStatus.ACTIVE) {
            log.error("Cart is not Active or already closed, not allowed to be purchased here: " + cartId);
            throw new CartAlreadyClosedException("Cart is already closed", cartId);
        }
        for (Item item : shoppingCart.getItems()) {
            if (item.getProduct().getQuantity() < item.getQuantity()) {
                throw new ItemOutOfStockException("Item Out of stock", item.getProduct().getId(), item.getProduct().getQuantity(), item.getQuantity());
            }
            item.getProduct().setQuantity(item.getProduct().getQuantity() - item.getQuantity());
        }
        shoppingCart.setStatus(ShoppingCartStatus.PURCHASED);
        log.debug("Making purchase to cart: " + shoppingCart);
        // Remove Quantity from
        shoppingCartRepository.save(shoppingCart);
    }
}
