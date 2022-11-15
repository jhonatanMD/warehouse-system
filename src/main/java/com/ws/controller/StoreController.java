package com.ws.controller;

import com.ws.entity.dto.SaleDto;
import com.ws.entity.dto.StoreDto;
import com.ws.entity.dto.data.StoreRequest;
import com.ws.service.IStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {

    private final IStoreService storeService;


    @GetMapping()
    public Flux<StoreDto> getAll(@RequestAttribute("headquarters") Long headquarters){
        return storeService.findAll(headquarters);
    }

    @PostMapping()
    public Mono<StoreDto> save(@RequestBody StoreRequest storeDto){
        return storeService.save(storeDto);
    }

    @PutMapping("/{id}")
    public Mono<StoreDto> update(@PathVariable Long id ,@RequestBody StoreRequest storeDto){
        return storeService.update(storeDto,id);
    }

    @GetMapping("/id/{id}")
    public Mono<StoreDto> getById(@PathVariable Long id){
        return storeService.findById(id);
    }


}
