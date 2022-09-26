package com.ws.service;

import com.ws.entity.dto.ProvidersDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProvidersService {

    Flux<ProvidersDto> findAll(Long id);
    Mono<ProvidersDto> save(ProvidersDto providers);
    Mono<ProvidersDto> update(ProvidersDto providers ,Long id);



}
