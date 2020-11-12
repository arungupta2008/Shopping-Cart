package com.saket.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;

public class ShoppingCartNotFoundException extends AbstractException {

    public ShoppingCartNotFoundException(String errorMessage, Long cartId) {
        super("Cart not found", HttpStatus.NOT_FOUND);
        StandardErrorDetail standardErrorDetail = new StandardErrorDetail("CartId", "" + cartId);
        super.setErrorDetails(Collections.singletonList(standardErrorDetail));
    }
}
