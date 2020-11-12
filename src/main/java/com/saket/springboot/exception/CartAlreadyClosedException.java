package com.saket.springboot.exception;


import org.springframework.http.HttpStatus;

import java.util.Collections;

public class CartAlreadyClosedException extends AbstractException {
    public CartAlreadyClosedException(String errorMessage, Long cartId) {
        super("Cart already closed", HttpStatus.CONFLICT);
        StandardErrorDetail standardErrorDetail = new StandardErrorDetail("CartId", "" + cartId);
        super.setErrorDetails(Collections.singletonList(standardErrorDetail));
    }
}
