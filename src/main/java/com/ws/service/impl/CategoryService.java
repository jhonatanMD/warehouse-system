package com.ws.service.impl;

import com.ws.entity.BrandEntity;
import com.ws.entity.CategoryEntity;
import com.ws.entity.dto.CategoryDto;
import com.ws.mapper.ICategoryMapper;
import com.ws.repository.CategoryRepository;
import com.ws.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final ICategoryMapper mapper;


    @Override
    public Flux<CategoryDto> findAll(Long id) {
        return Flux.fromIterable(categoryRepository.findByHeadquarters_Id(id))
                //.filter(status::test)
                .map(mapper::toDto);
    }

    @Override
    public Mono<CategoryDto> save(CategoryDto categoryDto) {
        return Mono.fromCallable(() ->categoryRepository.save(mapper.toEntity(categoryDto)))
                .map(mapper::toDto);
    }

    @Override
    public Mono<CategoryDto> update(CategoryDto categoryDto, Long id) {
        categoryDto.setId(id);
        return Mono.fromCallable(() ->categoryRepository.save(mapper.toEntity(categoryDto)))
                .map(mapper::toDto);
    }

    private Predicate<CategoryEntity> status = p -> p.getStatus();

}
