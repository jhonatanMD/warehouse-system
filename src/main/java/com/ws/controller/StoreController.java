package com.ws.controller;

import com.ws.entity.dto.StoreDto;
import com.ws.entity.dto.data.StoreRequest;
import com.ws.service.IStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
