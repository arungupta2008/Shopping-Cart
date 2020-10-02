package com.saket.springboot.controller;

import com.saket.springboot.domain.ShoppingCart;
import com.saket.springboot.dto.ShoppingCartDTO;
import com.saket.springboot.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ShoppingCart addProductItem(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        return shoppingCartService.saveProducts(shoppingCartDTO);
    }


    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<ShoppingCart> getAll(){
        return shoppingCartService.findAll();
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json", value ="/{id}")
    public ShoppingCart updateProductItem(@RequestBody ShoppingCartDTO shoppingCartDTO, @PathVariable("id") Long ids) {
        return shoppingCartService.updateProduct(shoppingCartDTO, ids);
    }

    @RequestMapping(method = RequestMethod.DELETE, value ="/{id}")
    public void deleteProductItem(@PathVariable("id") Long ids) {
        shoppingCartService.deleteProduct(ids);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void ClearCart( Object object) {
        shoppingCartService.clearShoppingCart(object);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/purchase/{id}")
    public void purchaseProducts(@PathVariable("id") Long id) {
        shoppingCartService.purchaseProducts(id);
    }
}
