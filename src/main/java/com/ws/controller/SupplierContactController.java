package com.ws.controller;

import com.ws.entity.dto.SupplierContactDto;
import com.ws.service.ISupplierContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/supplier-contact")
@RequiredArgsConstructor
public class SupplierContactController {

    private final ISupplierContactService supplierContactService;

    @GetMapping()
    public Flux<SupplierContactDto> getAll(){
        return supplierContactService.findAll();
    }

    @PostMapping()
    public Mono<SupplierContactDto> save(@RequestBody SupplierContactDto supplierContact){
        return supplierContactService.save(supplierContact);
    }

    @PutMapping("/{id}")
    public Mono<SupplierContactDto> update(@PathVariable Long id, @RequestBody SupplierContactDto supplierContact){
        return supplierContactService.update(supplierContact,id);
    }

    @PutMapping("/status/{id}")
    public Mono<SupplierContactDto> status(@PathVariable Long id){
        return supplierContactService.status(id);
    }
}
