package com.ws.service;

import com.ws.entity.dto.MaterialDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IMaterialService {

    Flux<MaterialDto> findAll(Long id);
    Mono<MaterialDto> save(MaterialDto materialDto);
    Mono<MaterialDto> update(MaterialDto materialDto ,Long id);

    MaterialDto findByName(long id , String name);


}
