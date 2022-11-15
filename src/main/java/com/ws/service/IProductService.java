package com.ws.service;

import com.ws.entity.dto.ProductDto;
import com.ws.entity.dto.data.AddProduct;
import com.ws.entity.dto.data.ProductData;
import com.ws.entity.dto.data.ProductDataResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {

    Flux<ProductDto> findAll(Long id);
    Mono<ProductDataResponse> find(Long id);
    Flux<ProductDto> findAllByStore(Long id);
    Mono<ProductDto> save(ProductData product);
    Mono<ProductDto> update(ProductData product ,Long id);
    Mono<ProductDto> addProduct(AddProduct addProduct , String flag);



}
