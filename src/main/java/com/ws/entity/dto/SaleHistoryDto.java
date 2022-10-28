package com.ws.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleHistoryDto  {

    private Long id;
    private Long saleId;
    private Long productId;
    private Long amount;
    private Boolean status =  true;
}
