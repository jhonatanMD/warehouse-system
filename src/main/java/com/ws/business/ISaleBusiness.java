package com.ws.business;

import com.ws.entity.dto.SaleDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ISaleBusiness {

    Flux<SaleDto> findAll(Long headquarters);
    Mono<SaleDto> save( SaleDto sale);
    Mono<SaleDto> findById( Long id);
}
