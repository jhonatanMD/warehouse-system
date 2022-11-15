package com.ws.service;

import com.ws.entity.dto.StoreDto;
import com.ws.entity.dto.data.StoreRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IStoreService {

    Flux<StoreDto> findAll(Long id);
    Mono<StoreDto> save(StoreRequest storeDto);
    Mono<StoreDto> update(StoreRequest storeDto ,Long id);

    Mono<StoreDto> findById(Long id);

}
