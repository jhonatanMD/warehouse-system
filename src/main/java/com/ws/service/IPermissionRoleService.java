package com.ws.service;

import com.ws.entity.dto.PermissionRoleDto;
import com.ws.entity.dto.PermissionRoleResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPermissionRoleService {

    Flux<PermissionRoleDto> findAll();
    Flux<PermissionRoleResponseDto> findByRol(Long rolId);
    Mono<PermissionRoleDto> save(PermissionRoleDto role);
    Mono<PermissionRoleDto> update(PermissionRoleDto role ,Long id);



}
