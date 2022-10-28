package com.ws.controller;

import com.ws.entity.dto.ProductDto;
import com.ws.entity.dto.data.AddProduct;
import com.ws.entity.dto.data.ProductData;
import com.ws.entity.dto.data.ProductDataResponse;
import com.ws.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/select")
    public Flux<ProductDataResponse> get(@RequestAttribute("headquarters") Long headquarters){
        return productService.find(headquarters);
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
    public Mono<ProductDto> save(@RequestAttribute("headquarters") Long headquarters, @RequestBody ProductData product){
        product.setHeadquarters(headquarters);
        return productService.save(product);
    }

    @PutMapping("/{id}")
    public Mono<ProductDto> update(@PathVariable Long id, @RequestAttribute("headquarters") Long headquarters, @RequestBody ProductData product){
        product.setHeadquarters(headquarters);
        return productService.update(product,id);
    }

    @PutMapping("/add")
    public Mono<ProductDto> addProducts(@RequestBody AddProduct product){

        return productService.addProduct(product,"+");
    }
}
