package com.ws.service.impl;

import com.ws.entity.dto.SupplierContactDto;
import com.ws.mapper.ISupplierContactMapper;
import com.ws.repository.SupplierContactRepository;
import com.ws.service.ISupplierContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class SupplierContactService implements ISupplierContactService {


    private final SupplierContactRepository supplierContactRepository;
    private final ISupplierContactMapper mapper;

    @Override
    public Flux<SupplierContactDto> findAll() {
        return Flux.fromIterable(supplierContactRepository.findAll())
                .map(mapper::toDto);
    }

    @Override
    public Mono<SupplierContactDto> save(SupplierContactDto supplierContact) {
        return Mono.fromCallable(() -> supplierContactRepository.save(mapper.toEntity(supplierContact)))
                .map(mapper::toDto);
    }

    @Override
    public Mono<SupplierContactDto> update(SupplierContactDto supplierContact, Long id) {
        return null;
    }
}
