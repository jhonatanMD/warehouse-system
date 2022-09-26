package com.ws.service.impl;

import com.ws.entity.dto.RoleDto;
import com.ws.mapper.IRoleMapper;
import com.ws.repository.RoleRepository;
import com.ws.service.IRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;
    private final IRoleMapper mapper;

    @Override
    public Flux<RoleDto> findAll(Long id) {
        return Flux.fromIterable(roleRepository.findByHeadquarters_Id(id))
                .map(mapper::toDto);
    }

    @Override
    public Mono<RoleDto> save(RoleDto role) {
        return Mono.fromCallable(() -> roleRepository.save(mapper.toEntity(role)))
                .map(mapper::toDto);
    }

    @Override
    public Mono<RoleDto> update(RoleDto role, Long id) {
        role.setId(id);
        return Mono.fromCallable(() -> roleRepository.save(mapper.toEntity(role)))
                .map(mapper::toDto);
    }
}
