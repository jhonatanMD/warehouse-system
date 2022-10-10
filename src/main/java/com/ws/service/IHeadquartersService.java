package com.ws.service;

import com.ws.entity.dto.HeadquartersDto;
import com.ws.entity.dto.data.HeadquartersResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IHeadquartersService {
    Flux<HeadquartersResponse> findAll(Long id);
    Mono<HeadquartersDto> save(HeadquartersDto headquarter);
    Mono<HeadquartersDto> update(HeadquartersDto headquarter ,Long id);


}
