package com.ws.entity.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SaleDetailDto {

    private Long id;
    private Long productId;
    private String productDetail;
    private BigDecimal priceUnit;
    private Long amount;
    private BigDecimal pTotal;
}


