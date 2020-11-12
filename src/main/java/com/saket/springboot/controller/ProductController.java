package com.saket.springboot.controller;

import com.saket.springboot.domain.Product;
import com.saket.springboot.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    /**
     * Get all Products
     * @return List of products
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Product> getAll() {
        return productService.getAllProducts();
    }

    /**
     * Searches the product by id, name and category.
     * @param key - ex. id, name, category
     * @param value - value of id name or category
     * @return list of products.
     */
    @RequestMapping(path = "/search", method = RequestMethod.GET, produces = "application/json")
    public List<Product> getProductsByQuery(@RequestParam("key") String key, @RequestParam("value") String value) {
        return productService.getProducts(key, value);
    }
}
