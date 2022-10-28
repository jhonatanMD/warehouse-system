package com.ws.service.impl;

import com.ws.entity.ProductEntity;
import com.ws.entity.dto.ProductDto;
import com.ws.entity.dto.data.AddProduct;
import com.ws.entity.dto.data.ProductData;
import com.ws.entity.dto.data.ProductDataResponse;
import com.ws.mapper.IProductMapper;
import com.ws.repository.ProductRepository;
import com.ws.service.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final IProductMapper mapper;


    @Override
    public Flux<ProductDto> findAll(Long id) {
        System.out.println(id);
        return Flux.fromIterable(productRepository.findByStoreHeaders(id))
                .filter(status::test)
                .map(mapper::toDto);
    }

    @Override
    public Flux<ProductDataResponse> find(Long id) {
        return Flux.fromIterable(productRepository.findByStoreHeaders(id))
                .filter(status::test)
                .map(mapper::toData);
    }

    @Override
    public Flux<ProductDto> findAllByStore(Long id) {
        return Flux.fromIterable(productRepository.findByStore_Id(id))
                .filter(status::test)
                .map(mapper::toDto);
    }

    @Override
    public Mono<ProductDto> save(ProductData product) {

       return Mono.fromCallable(() -> productRepository.findProduct(product.getHeadquarters()
                       ,product.getMiniCode(), product.getStore(),product.getBrand(),product.getCategory()
                       , product.getMaterial(), product.getType()).size() <= 0)
               .filter(Boolean::booleanValue)
               .map(r -> productRepository.save(mapper.toEntity(product)))
               .map(mapper::toDto)
               .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.CONFLICT,"Error al registrar producto , ya existe")));

    }

    @Override
    public Mono<ProductDto> update(ProductData product, Long id) {
        product.setId(id);
        return Mono.fromCallable(() -> productRepository.findProduct(product.getHeadquarters()
                        ,product.getMiniCode(), product.getStore(),product.getBrand(),product.getCategory()
                        , product.getMaterial(), product.getType()).size() <= 0)
                .filter(Boolean::booleanValue)
                .map(r -> productRepository.save(mapper.toEntity(product)))
                .map(mapper::toDto);

    }

    @Override
    public Mono<ProductDto> addProduct(AddProduct addProduct  , String flag) {

        Optional<ProductEntity> entity = productRepository.findById(addProduct.getProductId());
        if (!entity.isPresent())
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Error producto no existe");

        return Mono.fromCallable(() -> entity.get())
                .map(product -> {
                    if(flag.equals("+"))
                        product.setStock(product.getStock() + addProduct.getCant());
                    if(flag.equals("-"))
                        product.setStock(product.getStock() - addProduct.getCant());
                    return mapper.toDto(productRepository.save(product));
                });
    }



    private Predicate<ProductEntity> status = p -> p.getStatus();

}
