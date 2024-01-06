package com.ws.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

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
    private CategoryDto category;
    private TypeDto type;
    private BrandDto brand;
    private MaterialDto material;
    private StoreDto store;
    private Boolean status = true;
    private HeadquartersDto headquarters;
}
