package com.ws.controller;

import com.ws.entity.dto.HeadquartersDto;
import com.ws.entity.dto.TypeDto;
import com.ws.service.impl.TypeService;
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
@RequestMapping("/type")
@RequiredArgsConstructor
public class TypeController {

    private final TypeService typeService;

    @GetMapping()
    public Flux<TypeDto> getAll(@RequestAttribute("headquarters") Long headquarters){
        return typeService.findAll(headquarters);
    }

    @PostMapping()
    public Mono<TypeDto> save(@RequestAttribute("headquarters") Long headquarters, @RequestBody TypeDto type){
        type.setHeadquarters(HeadquartersDto.builder().id(headquarters).build());
        return typeService.save(type);
    }
}
