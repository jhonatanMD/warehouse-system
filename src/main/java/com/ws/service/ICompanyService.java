package com.ws.service;

import com.ws.entity.dto.CompanyDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICompanyService {

    Flux<CompanyDto> findAll();
    Mono<CompanyDto> save(CompanyDto company);
    Mono<CompanyDto> update(CompanyDto company ,Long id);



}
