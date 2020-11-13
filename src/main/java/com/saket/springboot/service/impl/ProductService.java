package com.saket.springboot.service.impl;

import com.saket.springboot.domain.Product;
import com.saket.springboot.exception.InvalidSearchKeySuppliedException;
import com.saket.springboot.exception.ProductNotFoundException;
import com.saket.springboot.repository.IProductRepository;
import com.saket.springboot.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional(Transactional.TxType.REQUIRED)
@Slf4j
public class ProductService implements IProductService {

    private final IProductRepository productRepository;
    private final List<Product> products;

    @Autowired
    public ProductService(IProductRepository IProductRepository) {
        this.productRepository = IProductRepository;
        products = new ArrayList<>();
        initProducts();
    }

    private void initProducts() {
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

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProducts(String productKey, String value) {
        List<Product> products = new ArrayList<>();
        switch (productKey.toLowerCase()) {
            case "id":
                products = Collections.singletonList(productRepository.findOne(Long.valueOf(value)));
                break;
            case "name":
                products = productRepository.findByName(value);
                break;
            case "category":
                products = productRepository.findByCategory(value);
                break;
            default:
                throw new InvalidSearchKeySuppliedException(productKey);
        }
        if (CollectionUtils.isEmpty(products)) {
            throw new ProductNotFoundException("Products not found", productKey, value);
        }
        return products;
    }

    @Override
    public Product getProductByProductId(Long productId) {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            log.error("Product not found " + productId);
            throw new ProductNotFoundException("Product not found", productId);
        }
        return product;
    }
}
