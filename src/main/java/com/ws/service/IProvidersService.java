package com.ws.service;

import com.ws.entity.dto.ProvidersDto;
import com.ws.entity.dto.data.ProvidersRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProvidersService {

    Flux<ProvidersDto> findAll(Long id);
    Mono<ProvidersDto> findById(Long id);
    Mono<ProvidersDto> save(ProvidersRequest providers);
    Mono<ProvidersDto> update(ProvidersRequest providers ,Long id);


    Mono<ProvidersDto> status(Long id);



}
