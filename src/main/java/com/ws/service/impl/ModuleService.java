package com.ws.service.impl;

import com.ws.entity.MaterialEntity;
import com.ws.entity.dto.ModuleDto;
import com.ws.mapper.IModuleMapper;
import com.ws.repository.ModuleRepository;
import com.ws.service.IModuleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

@Slf4j
@Service
@RequiredArgsConstructor
public class ModuleService implements IModuleService {

    private final ModuleRepository moduleRepository;
    private final IModuleMapper mapper;


    @Override
    public Flux<ModuleDto> findAll() {
        return Flux.fromIterable(moduleRepository.findAll())
                .map(mapper::toDto);
    }

    @Override
    public Mono<ModuleDto> save(ModuleDto moduleDto) {
        return Mono.fromCallable(() -> moduleRepository.save(mapper.toEntity(moduleDto)))
                .map(mapper::toDto);
    }

    @Override
    public Mono<ModuleDto> update(ModuleDto moduleDto, Long id) {

        moduleDto.setId(id);

        return Mono.fromCallable(() -> moduleRepository.save(mapper.toEntity(moduleDto)))
                .map(mapper::toDto);
    }


}
