package com.ws.entity.dto.data;

import lombok.Data;

@Data
public class ProductStockData {
    private String miniCode;
    private String name;
    private Long stock;
}
