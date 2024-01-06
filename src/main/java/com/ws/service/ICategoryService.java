package com.ws.service;

import com.ws.entity.dto.CategoryDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICategoryService {

    Flux<CategoryDto> findAll(Long id);
    Mono<CategoryDto> save(CategoryDto categoryDto);
    Mono<CategoryDto> update(CategoryDto categoryDto ,Long id);

    CategoryDto findByName(long id , String name);

}
