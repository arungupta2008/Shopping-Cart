package com.saket.springboot.exception;

import org.springframework.http.HttpStatus;

import java.util.Collections;

public class ItemOutOfStockException extends AbstractException {
    public ItemOutOfStockException(String errorMessage, Long itemId, Integer currentQuantity, Integer requestQuantity) {
        super("Item out of stock", HttpStatus.CONFLICT);
        StandardErrorDetail standardErrorDetail = new StandardErrorDetail(errorMessage, "itemId : " + itemId + " Current Stock : " + currentQuantity + " Requested Quantity: " + requestQuantity);
        super.setErrorDetails(Collections.singletonList(standardErrorDetail));
    }
}
