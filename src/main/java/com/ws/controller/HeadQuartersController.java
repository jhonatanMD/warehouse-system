package com.ws.controller;

import com.ws.entity.dto.CompanyDto;
import com.ws.entity.dto.HeadquartersDto;
import com.ws.entity.dto.data.HeadquartersResponse;
import com.ws.service.IHeadquartersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public Flux<HeadquartersResponse> getAll(@RequestAttribute("company") Long id){
        return headquartersService.findAll(id);
    }

    @PostMapping()
    public Mono<HeadquartersDto> save(@RequestAttribute("company") Long id , @RequestBody HeadquartersDto headquarters){
        headquarters.setCompany(CompanyDto.builder().id(id).build());
        return headquartersService.save(headquarters);
    }
    @PutMapping("/{id}")
    public Mono<HeadquartersDto> update(@RequestAttribute("company") Long company , @PathVariable("id") Long id, @RequestBody HeadquartersDto headquarters){
        headquarters.setCompany(CompanyDto.builder().id(company).build());
        return headquartersService.update(headquarters,id);
    }
}
