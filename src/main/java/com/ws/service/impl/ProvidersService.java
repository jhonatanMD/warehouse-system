package com.ws.service.impl;

import com.ws.entity.ProvidersEntity;
import com.ws.entity.dto.ProvidersDto;
import com.ws.entity.dto.data.ProvidersRequest;
import com.ws.mapper.IProvidersMapper;
import com.ws.repository.ProvidersRepository;
import com.ws.service.IProvidersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.function.Predicate;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProvidersService implements IProvidersService {

    private final ProvidersRepository providersRepository;
    private final IProvidersMapper mapper;


    @Override
    public Flux<ProvidersDto> findAll(Long id) {
        return Flux.fromIterable(providersRepository.findByCompany_Id(id))

                .map(mapper::toDto);
    }

    @Override
    public Mono<ProvidersDto> findById(Long id) {

        Optional<ProvidersEntity> s =  providersRepository.findById(id);

        if(s.isPresent())
            return Mono.fromCallable(() -> s.get())
                    //.filter(status::test)
                    .map(mapper::toDto);

        return Mono.empty();
    }

    @Override
    public Mono<ProvidersDto> save(ProvidersRequest providers) {
        return Mono.fromCallable(() -> providersRepository.save(mapper.toEntity(providers)))
                .map(mapper::toDto);
    }

    @Override
    public Mono<ProvidersDto> update(ProvidersRequest providers, Long id) {

        Optional<ProvidersEntity> providersEntity =  providersRepository.findById(id);

        if(!providersEntity.isPresent())
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Error proveedor no existe");

        return Mono.fromCallable(() -> providersEntity.get())
                .map(pro -> {
                    providers.setId(id);
                    return mapper.toDto(providersRepository.save(mapper.toEntity(providers)));
                });
    }

    @Override
    public Mono<ProvidersDto> status(Long id) {
        return findById(id).map(p ->{
            p.setStatus(!p.getStatus());
            return mapper.toDto(providersRepository.save(mapper.toEntity(p)));
        });

    }

    private Predicate<ProvidersEntity> status = p -> p.getStatus();


}
