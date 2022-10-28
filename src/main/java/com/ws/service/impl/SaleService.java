package com.ws.service.impl;

import com.ws.entity.dto.SaleDto;
import com.ws.mapper.ISaleMapper;
import com.ws.repository.SaleRepository;
import com.ws.service.ISaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class SaleService implements ISaleService {

    private final SaleRepository saleRepository;
    private final ISaleMapper mapper;
    @Override
    public Flux<SaleDto> findAll(Long headquarters) {
        return Flux.fromIterable(saleRepository.findAll())
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public Mono<SaleDto> save(SaleDto saleDto) {
        return Mono.fromCallable(() -> saleRepository.save(mapper.toEntity(saleDto)))
                .map(mapper::toDto);
    }
}
