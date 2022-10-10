package com.ws.service.impl;

import com.ws.entity.RoleEntity;
import com.ws.entity.dto.RoleDto;
import com.ws.mapper.IRoleMapper;
import com.ws.repository.RoleRepository;
import com.ws.service.IRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;
    private final IRoleMapper mapper;

    @Override
    public Flux<RoleDto> findAll(Long id) {
        return Flux.fromIterable(roleRepository.findByHeadquarters_Id(id))
                .filter(status::test)
                .map(mapper::toDto);
    }

    @Override
    public Mono<RoleDto> findById(Long id) {
        return Mono.fromCallable(() -> roleRepository.findById(id).orElse(null))
                .filter(status::test)
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

    @Override
    public Mono<Boolean> updateStatus(Long id) {

        return Mono.fromCallable(() -> roleRepository.findById(id)
                .map(r -> {
                    r.setStatus(r.getStatus() ? Boolean.FALSE : Boolean.TRUE);
                    return mapper.toDto(roleRepository.save(r));
                }).isPresent());
    }

    private Predicate<RoleEntity> status = p -> p.getStatus();


}
