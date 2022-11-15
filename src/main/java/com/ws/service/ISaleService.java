package com.ws.service;

import com.ws.entity.dto.SaleDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ISaleService {

    Flux<SaleDto> findAll(Long headquarters);
    Mono<SaleDto> save(SaleDto saleDto);

    Mono<SaleDto> findById( Long id);
}
