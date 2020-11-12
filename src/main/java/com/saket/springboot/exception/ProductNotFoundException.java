package com.saket.springboot.exception;

import org.springframework.http.HttpStatus;

import java.util.Collections;

public class ProductNotFoundException extends AbstractException {

    public ProductNotFoundException(String errorMessage, Long productId) {
        super("Product not found", HttpStatus.NOT_FOUND);
        StandardErrorDetail standardErrorDetail = new StandardErrorDetail("ProductId", "" + productId);
        super.setErrorDetails(Collections.singletonList(standardErrorDetail));
    }
}
