package com.ws.service.impl;

import com.ws.entity.PermissionRoleEntity;
import com.ws.entity.dto.PermissionRoleDto;
import com.ws.entity.dto.PermissionRoleResponseDto;
import com.ws.entity.dto.data.PermissionRoleRequest;
import com.ws.mapper.IPermissionRoleMapper;
import com.ws.repository.PermissionRoleRepository;
import com.ws.service.IPermissionRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Slf4j
@Service
@RequiredArgsConstructor
public class PermissionRoleService implements IPermissionRoleService {

    private final PermissionRoleRepository permissionRoleRepository;
    private final IPermissionRoleMapper mapper;

    @Override
    public Flux<PermissionRoleDto> findAll() {
        return Flux.fromIterable(permissionRoleRepository.findAll())
                //.filter(status::test)
                .map(mapper::toDto);
    }

    @Override
    public Flux<PermissionRoleResponseDto> findByRol(Long rolId) {
        return Flux.fromIterable(permissionRoleRepository.findByRoleId(rolId))
                .map(mapper::toDtoResponse);
    }

    @Override
    public Mono<PermissionRoleDto> save(PermissionRoleRequest role) {
        return Mono.fromCallable(() -> permissionRoleRepository.save(mapper.toEntity(role)))
                .map(mapper::toDto);
    }

    @Override
    public Flux<PermissionRoleDto> save(List<PermissionRoleRequest> role , Long idRole) {
        return  Flux.fromIterable(role)
                .map(ro -> {
                    ro.setRole(idRole);
                    return ro;
                })
                .map(res -> mapper.toDto(permissionRoleRepository.save(mapper.toEntity(res))));
    }

    @Override
    public Mono<Boolean> update(List<PermissionRoleRequest> roles ,Long id) {

        return Flux.fromIterable(roles)
                .map(p -> {
                    p.setRole(id);
                    return p;
                })
                .map(mapper::toEntity)
                .filter(p -> p.getRole().getId() == id)
               // .filter(p -> permissionRoleRepository.findByRole_IdAndModules_IdAndId(id,p.getModules().getId(),p.getId()).isPresent())
                .collectList()
                .map(permissionRoleRepository::saveAll)
                .map(res -> res.size() > 0);
    }

    @Override
    public Mono<PermissionRoleDto> update(PermissionRoleDto role, Long id) {
        role.setId(id);
        return Mono.fromCallable(() -> permissionRoleRepository.save(mapper.toEntity(role)))
                .map(mapper::toDto);
    }

    @Override
    public void delete(Long id) {
        permissionRoleRepository.deleteByRoleId(id);
    }


    private Predicate<PermissionRoleEntity> status = p -> p.getStatus();

}
