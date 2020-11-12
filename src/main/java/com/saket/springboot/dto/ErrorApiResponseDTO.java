package com.saket.springboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saket.springboot.exception.AErrorDetail;
import lombok.Getter;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Getter
public class ErrorApiResponseDTO {
    @JsonProperty(value = "timestamp")
    public long timestamp;

    @JsonProperty(value = "error_message")
    private String errorMessage;

    @JsonProperty(value = "error_details")
    private final List<AErrorDetail> errorDetails;

    public ErrorApiResponseDTO(String errorMessage, List<AErrorDetail> errorDetails) {
        this.timestamp = Instant.now().toEpochMilli();
        this.errorDetails = (errorDetails != null) ? errorDetails : Collections.emptyList();
        this.errorMessage = errorMessage;
    }
}
