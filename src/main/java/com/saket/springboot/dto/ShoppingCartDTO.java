package com.saket.springboot.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class ShoppingCartDTO {
    private Long userId;
    private Long productId;
    private Integer stock;
    private String status;
}
