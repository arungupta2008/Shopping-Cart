package com.saket.springboot.exception;

import com.saket.springboot.dto.ErrorApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice("com.saket.springboot")
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AbstractException.class)
    public ResponseEntity<ErrorApiResponse> handleAbstractServiceException(AbstractException ex) {
        ErrorApiResponse errorApiResponse = new ErrorApiResponse(ex.getErrorMessage(), ex.getErrorDetails());
        return new ResponseEntity<ErrorApiResponse>(errorApiResponse, ex.getHttpStatus());
    }
}
