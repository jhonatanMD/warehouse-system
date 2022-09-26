package com.ws.service;

import com.ws.entity.dto.HeadquartersDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IHeadquartersService {
    Flux<HeadquartersDto> findAll();
    Mono<HeadquartersDto> save(HeadquartersDto headquarter);
    Mono<HeadquartersDto> update(HeadquartersDto headquarter ,Long id);
}
