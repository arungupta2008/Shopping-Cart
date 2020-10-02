package com.saket.springboot.service;

import com.saket.springboot.domain.Product;
import com.saket.springboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sahan on 4/8/2016.
 */
@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private static List<Product> products = new ArrayList<>();

    static {
        products.add(new Product(35.75d, 1000, "Pears baby soap for Kids", "Grocery", "Soap"));
        products.add(new Product(45.50d, 500, "Signal Tooth Brushes Size in (L, M, S)", "Grocery", "Tooth Brushe"));
        products.add(new Product(1500.0d, 100, "Casual Shirt imported from France", "Wear", "Shirt"));
        products.add(new Product(1000.0d, 400, "Leather bag imported from USA ", "Office", "Office Bag"));
        products.add(new Product(450.0d, 800, "Hot Water Bottles", "Home", "Bottle"));
        products.add(new Product(2500.0d, 800, "Imported wrist watches from swiss", "Watch", "Wrist Watch"));
        products.add(new Product(45000.0d, 800, "3G/4G capability", "Mobile", "Mobile Phone"));
        products.add(new Product(300.0d, 800, "Head and Shoulders Shampoo", "Grocery", "Shampoo"));
        products.add(new Product(550.0d, 800, "Imported Leather Wallets from AUS", "Wallet", "Leather Wallets"));
        products.add(new Product(85000.0d, 800, "Imported Canon camera from USA", "Camera", "Camera"));
    }

    public void saveInitialBatch() {
        productRepository.save(products);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findByQuery(String productKey, String value) {
        switch (productKey.toLowerCase()) {
            case "id":
                return Arrays.asList(productRepository.getOne(Long.valueOf(value)));
            case "name":
                return productRepository.findByName(value);
            case "category":
                return productRepository.findByCategory(value);
            default:
                return new ArrayList<>();
        }
    }


}
