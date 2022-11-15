package com.ws.service;

import com.ws.entity.dto.PermissionRoleDto;
import com.ws.entity.dto.PermissionRoleResponseDto;
import com.ws.entity.dto.data.PermissionRoleRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IPermissionRoleService {

    Flux<PermissionRoleDto> findAll();
    Flux<PermissionRoleResponseDto> findByRol(Long rolId);
    Mono<PermissionRoleDto> save(PermissionRoleRequest role);
    Flux<PermissionRoleDto> save(List<PermissionRoleRequest> role , Long idRole);
    Mono<Boolean> update(List<PermissionRoleRequest> roles ,Long id);
    Mono<PermissionRoleDto> update(PermissionRoleDto role ,Long id);
    void delete(Long id);



}
