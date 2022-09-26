package com.ws.controller;

import com.ws.entity.dto.BrandDto;
import com.ws.entity.dto.HeadquartersDto;
import com.ws.service.IBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandController {

    private final IBrandService brandService;

    @GetMapping()
    public Flux<BrandDto> getAll(@RequestAttribute("headquarters") Long headquarters){
        return brandService.findAll(headquarters);
    }

    @PostMapping()
    public Mono<BrandDto> save(@RequestAttribute("headquarters") Long headquarters, @RequestBody BrandDto brand){
        brand.setHeadquarters(HeadquartersDto.builder().id(headquarters).build());
        return brandService.save(brand);
    }
}
