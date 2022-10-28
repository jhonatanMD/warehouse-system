package com.ws.entity.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SaleDto {

    private Long id;

    private String saleCod;
    private String srs;
    private String address;
    private String payCondition;
    private String referralGuide;
    private String customerDoc;
    private BigDecimal pSubTotal;
    private BigDecimal pIgv;
    private BigDecimal pTotal;
    private Boolean status;

    private HeadquartersDto headquarters;

    private List<SaleDetailDto> saleDetails;
}
