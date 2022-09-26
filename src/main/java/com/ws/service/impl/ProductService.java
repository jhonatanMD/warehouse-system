package com.ws.service.impl;

import com.ws.entity.dto.ProductDto;
import com.ws.mapper.IProductMapper;
import com.ws.repository.ProductRepository;
import com.ws.service.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final IProductMapper mapper;


    @Override
    public Flux<ProductDto> findAll(Long id) {
        return Flux.fromIterable(productRepository.findByHeadquarters_Id(id))
                .map(mapper::toDto);
    }

    @Override
    public Flux<ProductDto> findAllByStore(Long id) {
        return Flux.fromIterable(productRepository.findByStore_Id(id))
                .map(mapper::toDto);
    }

    @Override
    public Mono<ProductDto> save(ProductDto product) {
        return Mono.fromCallable(() -> productRepository.save(mapper.toEntity(product)))
                .map(mapper::toDto);
    }

    @Override
    public Mono<ProductDto> update(ProductDto product, Long id) {
        return null;
    }
}
