package com.ws.service.impl;

import com.ws.entity.HeadquartersEntity;
import com.ws.entity.StoreEntity;
import com.ws.entity.dto.StoreDto;
import com.ws.entity.dto.data.StoreRequest;
import com.ws.mapper.IStoreMapper;
import com.ws.repository.StoreRepository;
import com.ws.service.IStoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreService implements IStoreService {

    private final StoreRepository storeRepository;
    private final IStoreMapper mapper;


    @Override
    public Flux<StoreDto> findAll(Long id) {
        return Flux.fromIterable(storeRepository.findStoreByHeadQuarters(id))
                .filter(status::test)
                .map(mapper::toDto);
    }

    @Override
    public Mono<StoreDto> save(StoreRequest storeDto) {
        return Mono.fromCallable(() -> storeRepository.save(mapper.dataToEntity(storeDto)))
                .map(mapper::toDto);
    }

    @Override
    public Mono<StoreDto> update(StoreRequest storeDto, Long id) {
        storeDto.setId(id);
        return Mono.fromCallable(() -> storeRepository.save(mapper.dataToEntity(storeDto)))
                .map(mapper::toDto);
    }

    @Override
    public Mono<StoreDto> findById(Long id) {


        Optional<StoreEntity> store = storeRepository.findById(id);

        if(store.isPresent())
        return Mono.fromCallable(() -> store.get())
                .map(mapper::toDto);

        return Mono.empty();
    }

    @Override
    public StoreDto findByName(long id, String name) {

        Optional<StoreEntity> store = storeRepository.findByHeadquartersIdAndName(id,name);

        if(store.isPresent())
            return store.map(mapper::toDto).get();

        return mapper.toDto(storeRepository.save(StoreEntity.builder()
                .name(name)
                .status(Boolean.TRUE)
                .headquarters(Set.of(HeadquartersEntity.builder()
                        .id(id)
                        .build()))
                .build()));
    }

    private Predicate<StoreEntity> status = p -> p.getStatus();

}
