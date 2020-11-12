package com.saket.springboot.exception;

import org.springframework.http.HttpStatus;

import java.util.Collections;

public class InvalidSearchKeySuppliedException extends AbstractException {
    public InvalidSearchKeySuppliedException(String key) {
        super("Invalid Product search key supplied", HttpStatus.BAD_REQUEST);
        StandardErrorDetail standardErrorDetail = new StandardErrorDetail("key", key);
        super.setErrorDetails(Collections.singletonList(standardErrorDetail));
    }
}
