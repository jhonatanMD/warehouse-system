package com.ws.service;

import com.ws.entity.dto.RoleDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IRoleService {

    Flux<RoleDto> findAll(Long id);
    Mono<RoleDto> save(RoleDto role);
    Mono<RoleDto> update(RoleDto role ,Long id);



}
