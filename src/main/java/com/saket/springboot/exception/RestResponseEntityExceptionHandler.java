package com.saket.springboot.exception;

import com.saket.springboot.dto.ErrorApiResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice("com.saket.springboot")
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AbstractException.class)
    public ResponseEntity<ErrorApiResponseDTO> handleAbstractServiceException(AbstractException ex) {
        ErrorApiResponseDTO errorApiResponseDTO = new ErrorApiResponseDTO(ex.getErrorMessage(), ex.getErrorDetails());
        return new ResponseEntity<ErrorApiResponseDTO>(errorApiResponseDTO, ex.getHttpStatus());
    }
}
