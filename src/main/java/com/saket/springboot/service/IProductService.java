package com.saket.springboot.service;

import com.saket.springboot.domain.Product;

import java.util.List;

public interface IProductService {
    void saveInitialBatch();
    List<Product> getAllProducts();
    List<Product> getProducts(String productKey, String value);
    Product getProductByProductId(Long productId);
}
