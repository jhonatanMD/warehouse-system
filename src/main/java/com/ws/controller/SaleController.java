package com.ws.controller;

import com.ws.business.ISaleBusiness;
import com.ws.entity.dto.HeadquartersDto;
import com.ws.entity.dto.SaleDto;
import com.ws.service.ISaleService;
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
}
