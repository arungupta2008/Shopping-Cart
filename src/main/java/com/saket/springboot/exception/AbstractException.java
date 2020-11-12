package com.saket.springboot.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public abstract class AbstractException extends RuntimeException {
    public AbstractException(String errorMessage, HttpStatus httpStatus) {
        this.errorMessage = errorMessage;
        this.errorDetails = errorDetails;
        this.httpStatus = httpStatus;
    }

    @JsonProperty("error_message")
    private String errorMessage;

    @JsonProperty("error_details")
    @Setter
    private List<AErrorDetail> errorDetails;

    private HttpStatus httpStatus;
}
