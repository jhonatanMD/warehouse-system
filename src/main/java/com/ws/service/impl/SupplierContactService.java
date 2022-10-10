package com.ws.service.impl;

import com.ws.entity.StoreEntity;
import com.ws.entity.SupplierContactEntity;
import com.ws.entity.dto.SupplierContactDto;
import com.ws.mapper.ISupplierContactMapper;
import com.ws.repository.SupplierContactRepository;
import com.ws.service.ISupplierContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

@Slf4j
@Service
@RequiredArgsConstructor
public class SupplierContactService implements ISupplierContactService {


    private final SupplierContactRepository supplierContactRepository;
    private final ISupplierContactMapper mapper;

    @Override
    public Flux<SupplierContactDto> findAll() {
        return Flux.fromIterable(supplierContactRepository.findAll())
                .filter(status::test)
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

    private Predicate<SupplierContactEntity> status = p -> p.getStatus();


}
