package com.saket.springboot.service;

import com.saket.springboot.domain.ShoppingCart;
import com.saket.springboot.dto.ShoppingCartDTO;
import com.saket.springboot.model.ShoppingCartStatus;

import java.util.List;

public interface IShoppingCartService {
    ShoppingCart addItemsInCart(ShoppingCartDTO shoppingCartDTO);
    List<ShoppingCart> getShoppingCarts(ShoppingCartStatus shoppingCartStatus);
    ShoppingCart updateProduct(ShoppingCartDTO shoppingCartDTO, Long cartId);
    void deleteShoppingCart(Long cartId);
    void clearAllShoppingCarts();
    void purchaseShoppingCart(Long cartId);
}
