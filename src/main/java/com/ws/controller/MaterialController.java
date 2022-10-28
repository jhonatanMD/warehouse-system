package com.ws.controller;

import com.ws.entity.dto.HeadquartersDto;
import com.ws.entity.dto.MaterialDto;
import com.ws.service.IMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/material")
@RequiredArgsConstructor
public class MaterialController {

    private final IMaterialService materialService;


    @GetMapping()
    public Flux<MaterialDto> getAll(@RequestAttribute("headquarters") Long headquarters){
        return materialService.findAll(headquarters);
    }

    @PostMapping()
    public Mono<MaterialDto> save(@RequestAttribute("headquarters") Long headquarters, @RequestBody MaterialDto material){
        material.setHeadquarters(HeadquartersDto.builder().id(headquarters).build());
        return materialService.save(material);
    }

    @PutMapping("/{id}")
    public Mono<MaterialDto> update(@PathVariable Long id,@RequestAttribute("headquarters") Long headquarters, @RequestBody MaterialDto material){
        material.setHeadquarters(HeadquartersDto.builder().id(headquarters).build());
        return materialService.update(material,id);
    }
}
