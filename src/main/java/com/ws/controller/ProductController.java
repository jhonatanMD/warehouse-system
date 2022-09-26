package com.ws.controller;

import com.ws.entity.dto.HeadquartersDto;
import com.ws.entity.dto.ProductDto;
import com.ws.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    @GetMapping()
    public Flux<ProductDto> getAll(@RequestAttribute("headquarters") Long headquarters){
        return productService.findAll(headquarters);
    }

    @GetMapping("/headquarters/{headquarters}")
    public Flux<ProductDto> getAllByHeadquarters(@PathVariable("headquarters") Long headquarters){
        return productService.findAll(headquarters);
    }

    @GetMapping("/store/{store}")
    public Flux<ProductDto> getAllByStore(@PathVariable("store") Long store){
        return productService.findAllByStore(store);
    }


    @PostMapping()
    public Mono<ProductDto> save(@RequestAttribute("headquarters") Long headquarters, @RequestBody ProductDto product){
        product.setHeadquarters(HeadquartersDto.builder().id(headquarters).build());
        return productService.save(product);
    }
}
