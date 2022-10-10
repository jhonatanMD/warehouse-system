package com.ws.service.impl;

import com.ws.entity.ProductEntity;
import com.ws.entity.ProvidersEntity;
import com.ws.entity.dto.ProvidersDto;
import com.ws.mapper.IProvidersMapper;
import com.ws.repository.ProvidersRepository;
import com.ws.service.IProvidersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProvidersService implements IProvidersService {

    private final ProvidersRepository providersRepository;
    private final IProvidersMapper mapper;


    @Override
    public Flux<ProvidersDto> findAll(Long id) {
        return Flux.fromIterable(providersRepository.findByHeadquarters_Id(id))
                .filter(status::test)
                .map(mapper::toDto);
    }

    @Override
    public Mono<ProvidersDto> save(ProvidersDto providers) {
        return Mono.fromCallable(() -> providersRepository.save(mapper.toEntity(providers)))
                .map(mapper::toDto);
    }

    @Override
    public Mono<ProvidersDto> update(ProvidersDto providers, Long id) {
        return null;
    }

    private Predicate<ProvidersEntity> status = p -> p.getStatus();


}
