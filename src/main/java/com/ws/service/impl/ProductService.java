package com.ws.service.impl;

import com.ws.entity.MaterialEntity;
import com.ws.entity.ProductEntity;
import com.ws.entity.dto.ProductDto;
import com.ws.entity.dto.data.ProductData;
import com.ws.mapper.IProductMapper;
import com.ws.repository.ProductRepository;
import com.ws.service.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final IProductMapper mapper;


    @Override
    public Flux<ProductDto> findAll(Long id) {
        return Flux.fromIterable(productRepository.findByHeadquarters_Id(id))
                .filter(status::test)
                .map(mapper::toDto);
    }

    @Override
    public Flux<ProductDto> findAllByStore(Long id) {
        return Flux.fromIterable(productRepository.findByStore_Id(id))
                .filter(status::test)
                .map(mapper::toDto);
    }

    @Override
    public Mono<ProductDto> save(ProductData product) {
        return Mono.fromCallable(() -> productRepository.save(mapper.toEntity(product)))
                .map(mapper::toDto);
    }

    @Override
    public Mono<ProductDto> update(ProductDto product, Long id) {
        return null;
    }

    private Predicate<ProductEntity> status = p -> p.getStatus();

}
