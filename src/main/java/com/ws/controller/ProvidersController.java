package com.ws.controller;

import com.ws.entity.dto.HeadquartersDto;
import com.ws.entity.dto.ProvidersDto;
import com.ws.service.IProvidersService;
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
@RequestMapping("/providers")
@RequiredArgsConstructor
public class ProvidersController {

    private final IProvidersService providersService;

    @GetMapping()
    public Flux<ProvidersDto> getAll(@RequestAttribute("headquarters") Long headquarters){
        return providersService.findAll(headquarters);
    }

    @PostMapping()
    public Mono<ProvidersDto> save(@RequestAttribute("headquarters") Long headquarters, @RequestBody ProvidersDto providers){
        providers.setHeadquarters(HeadquartersDto.builder().id(headquarters).build());
        return providersService.save(providers);
    }
}
