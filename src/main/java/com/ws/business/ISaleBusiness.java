package com.ws.business;

import com.ws.entity.dto.SaleDashBoardDto;
import com.ws.entity.dto.SaleDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ISaleBusiness {

    Flux<SaleDto> findAll(Long headquarters);
    Mono<SaleDto> save( SaleDto sale);
    Mono<SaleDto> findById( Long id);

    Mono<List<SaleDashBoardDto>>  getSaleByDate(Long headquarters);
}
