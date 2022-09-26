package com.ws.service;

import com.ws.entity.dto.ModuleDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IModuleService {

    Flux<ModuleDto> findAll();
    Mono<ModuleDto> save(ModuleDto moduleDto);
    Mono<ModuleDto> update(ModuleDto moduleDto ,Long id);



}
