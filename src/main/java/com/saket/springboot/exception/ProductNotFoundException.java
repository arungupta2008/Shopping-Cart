package com.saket.springboot.exception;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Collections;

public class ProductNotFoundException extends AbstractException {

    public ProductNotFoundException(String errorMessage, Long productId) {
        super("Product not found", HttpStatus.NOT_FOUND);
        StandardErrorDetail standardErrorDetail = new StandardErrorDetail("ProductId", "" + productId);
        super.setErrorDetails(Collections.singletonList(standardErrorDetail));
    }

    public ProductNotFoundException(String errorMessage, String key, String value) {
        super("Product not found", HttpStatus.NOT_FOUND);
        StandardErrorDetail standardErrorDetailKey = new StandardErrorDetail("key", key);
        StandardErrorDetail standardErrorDetailValue = new StandardErrorDetail("value", value);
        super.setErrorDetails(Arrays.asList(standardErrorDetailKey, standardErrorDetailValue));
    }
}
