package com.ws.service.impl;

import com.ws.entity.HeadquartersEntity;
import com.ws.entity.MaterialEntity;
import com.ws.entity.dto.MaterialDto;
import com.ws.mapper.IMaterialMapper;
import com.ws.repository.MaterialRepository;
import com.ws.service.IMaterialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

@Slf4j
@Service
@RequiredArgsConstructor
public class MaterialService implements IMaterialService {

    private final MaterialRepository materialRepository;
    private final IMaterialMapper mapper;


    @Override
    public Flux<MaterialDto> findAll(Long id) {
        return Flux.fromIterable(materialRepository.findByHeadquarters_Id(id))
                //.filter(status::test)
                .map(mapper::toDto);
    }

    @Override
    public Mono<MaterialDto> save(MaterialDto materialDto) {
        return Mono.fromCallable(() -> materialRepository.save(mapper.toEntity(materialDto)))
                .map(mapper::toDto);
    }

    @Override
    public Mono<MaterialDto> update(MaterialDto materialDto, Long id) {
        materialDto.setId(id);
        return Mono.fromCallable(() -> materialRepository.save(mapper.toEntity(materialDto)))
                .map(mapper::toDto);
    }

    private Predicate<MaterialEntity> status = p -> p.getStatus();

}
