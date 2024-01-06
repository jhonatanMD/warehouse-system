package com.ws.service;

import com.ws.entity.dto.TypeDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITypeService {

    Flux<TypeDto> findAll(Long id);
    Mono<TypeDto> save(TypeDto typeDto);
    Mono<TypeDto> update(TypeDto typeDto ,Long id);

    TypeDto findByName(long id , String name);


}
