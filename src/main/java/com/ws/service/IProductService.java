package com.ws.service;

import com.ws.entity.dto.ProductDto;
import com.ws.entity.dto.data.ProductData;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {

    Flux<ProductDto> findAll(Long id);
    Flux<ProductDto> findAllByStore(Long id);
    Mono<ProductDto> save(ProductData product);
    Mono<ProductDto> update(ProductDto product ,Long id);



}
