package com.ws.service.impl;

import com.ws.entity.dto.HeadquartersDto;
import com.ws.mapper.IHeadquartersMapper;
import com.ws.repository.HeadquartersRepository;
import com.ws.service.IHeadquartersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class HeadquartersService implements IHeadquartersService {

    private final HeadquartersRepository headquartersRepository;
    private final IHeadquartersMapper headquartersMapper;

    @Override
    public Flux<HeadquartersDto> findAll() {
        return Flux.fromIterable(headquartersRepository.findAll())
                .map(headquartersMapper::toDto);
    }

    @Override
    public Mono<HeadquartersDto> save(HeadquartersDto headquarter) {
        return Mono.fromCallable(() -> headquartersRepository.save(headquartersMapper.toEntity(headquarter)))
                .map(headquartersMapper::toDto);
    }

    @Override
    public Mono<HeadquartersDto> update(HeadquartersDto headquarter, Long id) {
        headquarter.setId(id);
        return Mono.fromCallable(() -> headquartersRepository.save(headquartersMapper.toEntity(headquarter)))
                .map(headquartersMapper::toDto);
    }
}
