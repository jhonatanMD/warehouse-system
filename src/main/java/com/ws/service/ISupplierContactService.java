package com.ws.service;

import com.ws.entity.dto.SupplierContactDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ISupplierContactService {

    Flux<SupplierContactDto> findAll();
    Mono<SupplierContactDto> save(SupplierContactDto supplierContact);
    Mono<SupplierContactDto> update(SupplierContactDto supplierContact ,Long id);
    Mono<SupplierContactDto> status(Long id);



}
