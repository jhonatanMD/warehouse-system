package com.ws.service;

import com.ws.entity.dto.StoreDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IStoreService {

    Flux<StoreDto> findAll(Long id);
    Mono<StoreDto> save(StoreDto storeDto);
    Mono<StoreDto> update(StoreDto storeDto ,Long id);



}
