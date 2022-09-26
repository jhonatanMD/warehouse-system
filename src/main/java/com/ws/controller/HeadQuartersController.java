package com.ws.controller;

import com.ws.entity.dto.CompanyDto;
import com.ws.entity.dto.HeadquartersDto;
import com.ws.service.IHeadquartersService;
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
@RequestMapping("/head-quarters")
@RequiredArgsConstructor
public class HeadQuartersController {

    private final IHeadquartersService headquartersService;

    @GetMapping()
    public Flux<HeadquartersDto> getAll(){
        return headquartersService.findAll();
    }

    @PostMapping()
    public Mono<HeadquartersDto> save(@RequestAttribute("company") Long id , @RequestBody HeadquartersDto headquarters){
        headquarters.setCompany(CompanyDto.builder().id(id).build());
        return headquartersService.save(headquarters);
    }
}
