package com.ws.service;

import com.ws.entity.dto.SaleDashBoardDto;
import com.ws.entity.dto.SaleDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ISaleService {

    Flux<SaleDto> findAll(Long headquarters);
    Mono<SaleDto> save(SaleDto saleDto);

    Mono<SaleDto> findById( Long id);

    Mono<List<SaleDashBoardDto>> findSaleByDate(Long headquarters);

    List<SaleDto> findBySaleByDate(String d1, String d2, Long headquarters);
}
