package com.saket.springboot.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StandardErrorDetail extends AErrorDetail {
    @JsonProperty("error_message")
    private final String errorMessage;

    @JsonProperty("reason")
    private final String reason;
}
