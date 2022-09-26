package com.ws.service.impl;

import com.ws.entity.dto.StoreDto;
import com.ws.mapper.IStoreMapper;
import com.ws.repository.StoreRepository;
import com.ws.service.IStoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreService implements IStoreService {

    private final StoreRepository storeRepository;
    private final IStoreMapper mapper;


    @Override
    public Flux<StoreDto> findAll(Long id) {
        return Flux.fromIterable(storeRepository.findStoreByHeadQuarters(id))
                .map(mapper::toDto);
    }

    @Override
    public Mono<StoreDto> save(StoreDto storeDto) {
        return Mono.fromCallable(() -> storeRepository.save(mapper.toEntity(storeDto)))
                .map(mapper::toDto);
    }

    @Override
    public Mono<StoreDto> update(StoreDto storeDto, Long id) {
        storeDto.setId(id);
        return Mono.fromCallable(() -> storeRepository.save(mapper.toEntity(storeDto)))
                .map(mapper::toDto);
    }
}
