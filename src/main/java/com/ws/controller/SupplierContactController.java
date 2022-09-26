package com.ws.controller;

import com.ws.entity.dto.SupplierContactDto;
import com.ws.service.ISupplierContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
