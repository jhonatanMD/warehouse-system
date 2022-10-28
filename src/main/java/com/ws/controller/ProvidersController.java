package com.ws.controller;

import com.ws.entity.dto.CompanyDto;
import com.ws.entity.dto.HeadquartersDto;
import com.ws.entity.dto.ProvidersDto;
import com.ws.entity.dto.data.ProvidersRequest;
import com.ws.service.IProvidersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/providers")
@RequiredArgsConstructor
public class ProvidersController {

    private final IProvidersService providersService;

    @GetMapping()
    public Flux<ProvidersDto> getAll(@RequestAttribute("company") Long company){
        return providersService.findAll(company);
    }

    @GetMapping("/{id}")
    public Mono<ProvidersDto> getById(@PathVariable("id") Long id){
        return providersService.findById(id);
    }
    @PostMapping()
    public Mono<ProvidersDto> save(@RequestAttribute("company") Long company, @RequestBody ProvidersRequest providers){
        providers.setCompany(CompanyDto.builder().id(company).build());
        return providersService.save(providers);
    }

    @PutMapping("/{id}")
    public Mono<ProvidersDto> update(@RequestAttribute("company") Long company, @RequestBody ProvidersRequest providers , @PathVariable("id") Long id){
        providers.setCompany(CompanyDto.builder().id(company).build());
        return providersService.update(providers,id);
    }

    @PutMapping("/status/{id}")
    public Mono<ProvidersDto> update(@PathVariable("id") Long id){
        return providersService.status(id);
    }



}
