package com.ws.controller;

import com.ws.entity.dto.CompanyDto;
import com.ws.service.ICompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final ICompanyService companyService;

    @GetMapping()
    public Flux<CompanyDto> getAll(){
        return companyService.findAll();
    }

    @PostMapping()
    public Mono<CompanyDto> save(@RequestBody CompanyDto company){

        return companyService.save(company);
    }
}
