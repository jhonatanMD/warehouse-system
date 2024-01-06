package com.ws.service.impl;

import com.ws.entity.BrandEntity;
import com.ws.entity.HeadquartersEntity;
import com.ws.entity.dto.BrandDto;
import com.ws.mapper.IBrandMapper;
import com.ws.repository.BrandRepository;
import com.ws.service.IBrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.function.Predicate;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrandService implements IBrandService {

    private final BrandRepository brandRepository;
    private final IBrandMapper mapper;


    @Override
    public Flux<BrandDto> findAll(Long id) {
        return Flux.fromIterable(brandRepository.findByHeadquarters_Id(id))
                //.filter(status::test)
                .map(mapper::toDto);
    }

    @Override
    public Mono<BrandDto> save(BrandDto brand) {
        return Mono.fromCallable(() -> brandRepository.save(mapper.toEntity(brand)))
                .map(mapper::toDto);
    }

    @Override
    public Mono<BrandDto> update(BrandDto brand, Long id) {
        brand.setId(id);
        return Mono.fromCallable(() -> brandRepository.save(mapper.toEntity(brand)))
                .map(mapper::toDto);
    }

    @Override
    public BrandDto findByName(long id, String name) {

        Optional<BrandEntity> brand = brandRepository.findTopByHeadquartersIdAndName(id,name);

        if(brand.isPresent())
            return brand.map(mapper::toDto).get();

        return mapper.toDto(brandRepository.save(BrandEntity.builder()
                .name(name)
                .description(name)
                .status(Boolean.TRUE)
                .headquarters(HeadquartersEntity.builder()
                        .id(id)
                        .build())
                .build()));

    }

    private Predicate<BrandEntity> status = p -> p.getStatus();


}
