package com.saket.springboot.controller;

import com.saket.springboot.domain.ShoppingCart;
import com.saket.springboot.dto.ShoppingCartDTO;
import com.saket.springboot.model.ShoppingCartStatus;
import com.saket.springboot.service.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    private final IShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(IShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ShoppingCart addProductItem(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        return shoppingCartService.addItemsInCart(shoppingCartDTO);
    }


    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<ShoppingCart> getAllActiveShoppingCart() {
        return shoppingCartService.getShoppingCarts(ShoppingCartStatus.ACTIVE);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json", value = "/{id}")
    public ShoppingCart updateProductItem(@RequestBody ShoppingCartDTO shoppingCartDTO, @PathVariable("id") Long cartId) {
        return shoppingCartService.updateProduct(shoppingCartDTO, cartId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteProductItem(@PathVariable("id") Long ids) {
        shoppingCartService.deleteShoppingCart(ids);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void clearCart() {
        shoppingCartService.clearAllShoppingCarts();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/purchase/{id}")
    public void purchaseProducts(@PathVariable("id") Long id) {
        shoppingCartService.purchaseShoppingCart(id);
    }
}
