package com.ws.service.impl;

import com.ws.entity.dto.PermissionRoleDto;
import com.ws.entity.dto.PermissionRoleResponseDto;
import com.ws.mapper.IPermissionRoleMapper;
import com.ws.repository.PermissionRoleRepository;
import com.ws.service.IPermissionRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class PermissionRoleService implements IPermissionRoleService {

    private final PermissionRoleRepository permissionRoleRepository;
    private final IPermissionRoleMapper mapper;

    @Override
    public Flux<PermissionRoleDto> findAll() {
        return Flux.fromIterable(permissionRoleRepository.findAll())
                .map(mapper::toDto);
    }

    @Override
    public Flux<PermissionRoleResponseDto> findByRol(Long rolId) {
        return Flux.fromIterable(permissionRoleRepository.findByRoleId(rolId))
                .map(mapper::toDtoResponse);
    }

    @Override
    public Mono<PermissionRoleDto> save(PermissionRoleDto role) {
        return Mono.fromCallable(() -> permissionRoleRepository.save(mapper.toEntity(role)))
                .map(mapper::toDto);
    }

    @Override
    public Mono<PermissionRoleDto> update(PermissionRoleDto role, Long id) {

        role.setId(id);

        return Mono.fromCallable(() -> permissionRoleRepository.save(mapper.toEntity(role)))
                .map(mapper::toDto);
    }
}
