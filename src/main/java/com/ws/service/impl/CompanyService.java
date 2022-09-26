package com.ws.service.impl;

import com.ws.entity.dto.CompanyDto;
import com.ws.mapper.ICompanyMapper;
import com.ws.repository.CompanyRepository;
import com.ws.service.ICompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CompanyService implements ICompanyService {

    private final CompanyRepository companyRepository;
    private final ICompanyMapper companyMapper;

    @Override
    public Flux<CompanyDto> findAll() {
        return Flux.fromIterable(companyRepository.findAll())
                .map(companyMapper::toDto);
    }

    @Override
    public Mono<CompanyDto> save(CompanyDto company) {

        return Mono.fromCallable(() -> companyRepository
                        .save(companyMapper.toEntity(company)))
                .map(companyMapper::toDto);
    }

    @Override
    public Mono<CompanyDto> update(CompanyDto company , Long id) {
        company.setId(id);
        return Mono.fromCallable(() -> companyRepository
                        .save(companyMapper.toEntity(company)))
                .map(companyMapper::toDto);
    }
}
