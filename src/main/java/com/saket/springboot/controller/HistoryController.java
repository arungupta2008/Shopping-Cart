package com.saket.springboot.controller;

import com.saket.springboot.domain.ShoppingCart;
import com.saket.springboot.model.ShoppingCartStatus;
import com.saket.springboot.service.IShoppingCartService;
import com.saket.springboot.service.impl.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {

    private final IShoppingCartService shoppingCartService;

    @Autowired
    public HistoryController(IShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<ShoppingCart> getAll() {
        return shoppingCartService.getShoppingCarts(ShoppingCartStatus.PURCHASED);
    }


}
