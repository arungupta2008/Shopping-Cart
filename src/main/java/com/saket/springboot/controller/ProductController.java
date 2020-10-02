package com.saket.springboot.controller;

import com.saket.springboot.domain.Product;
import com.saket.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Product> getAll() {
        return productService.findAll();
    }

    @RequestMapping(path = "/search", method = RequestMethod.GET, produces = "application/json")
    public List<Product> getProductsByQuery(@RequestParam("key") String key, @RequestParam("value") String value) {
        return productService.findByQuery(key, value);
    }
}
