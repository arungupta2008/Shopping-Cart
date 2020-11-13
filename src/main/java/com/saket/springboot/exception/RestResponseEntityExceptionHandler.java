package com.saket.springboot.exception;

import com.saket.springboot.dto.ErrorApiResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.Random;
import java.util.UUID;

@Slf4j
@ControllerAdvice("com.saket.springboot")
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorApiResponseDTO> handleAllUnhandelledExceptions(Exception exception) {

        String errorId = UUID.randomUUID().toString();
        logger.error("Unhandled Exception with error id: " + errorId, exception);
        StandardErrorDetail standardErrorDetail = new StandardErrorDetail("ErrorId", errorId);
        ErrorApiResponseDTO errorApiResponseDTO = new ErrorApiResponseDTO("Something went wrong, please" +
                "contact admin", Collections.singletonList(standardErrorDetail));
        return new ResponseEntity<>(errorApiResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AbstractException.class)
    public ResponseEntity<ErrorApiResponseDTO> handleAbstractServiceException(AbstractException ex) {
        ErrorApiResponseDTO errorApiResponseDTO = new ErrorApiResponseDTO(ex.getErrorMessage(), ex.getErrorDetails());
        return new ResponseEntity<>(errorApiResponseDTO, ex.getHttpStatus());
    }
}
