package com.ws.service;

import com.ws.entity.dto.BrandDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBrandService {

    Flux<BrandDto> findAll(Long id);
    Mono<BrandDto> save(BrandDto brand);
    Mono<BrandDto> update(BrandDto brand ,Long id);



}
