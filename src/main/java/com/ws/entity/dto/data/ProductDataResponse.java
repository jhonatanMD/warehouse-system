package com.ws.entity.dto.data;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDataResponse {

    private Long id;
    private String miniCode;
    private String name;
    private String description;
    private BigDecimal finalCost;
    private Long stock;
    private String category;
    private String type;
    private String brand;
    private String material;
    private String store;
    private Boolean status = true;
    private String headquarters;
}
