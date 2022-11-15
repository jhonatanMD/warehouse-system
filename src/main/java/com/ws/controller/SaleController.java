package com.ws.controller;

import com.ws.business.ISaleBusiness;
import com.ws.entity.dto.HeadquartersDto;
import com.ws.entity.dto.SaleDto;
import com.ws.service.ISaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/sale")
@RequiredArgsConstructor
public class SaleController {

    private final ISaleBusiness saleBusiness;

    @GetMapping()
    public Flux<SaleDto> getAll(@RequestAttribute("headquarters") Long headquarters){
        return saleBusiness.findAll(headquarters);
    }


    @PostMapping()
    public Mono<SaleDto> save(@RequestAttribute("headquarters") Long headquarters, @RequestBody SaleDto sale){
        sale.setHeadquarters(HeadquartersDto.builder().id(headquarters).build());
        return saleBusiness.save(sale);
    }

    @GetMapping("/id/{id}")
    public Mono<SaleDto> getById(@PathVariable Long id){
        return saleBusiness.findById(id);
    }
}
