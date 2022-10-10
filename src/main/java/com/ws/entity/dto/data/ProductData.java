package com.ws.entity.dto.data;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductData {

    private Long id;
    private String miniCode;
    private String name;
    private String description;
    private BigDecimal costSoles;
    private BigDecimal costDollars;
    private BigDecimal technicalPrice;
    private BigDecimal finalCost;
    private BigDecimal profitableNessCCT;
    private BigDecimal profitableNessCCC;
    private Long stock;
    private Long category;
    private Long type;
    private Long brand;
    private Long material;
    private Long store;
    private Boolean status = true;
    private Long headquarters;
}
