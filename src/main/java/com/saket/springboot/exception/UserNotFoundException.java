package com.saket.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;

public class UserNotFoundException extends AbstractException {
    public UserNotFoundException(String errorMessage, Long userId) {
        super("User not found", HttpStatus.NOT_FOUND);
        StandardErrorDetail standardErrorDetail = new StandardErrorDetail("UserId", "" + userId);
        super.setErrorDetails(Collections.singletonList(standardErrorDetail));
    }
}
