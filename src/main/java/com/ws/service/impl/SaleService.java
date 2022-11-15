package com.ws.service.impl;

import com.ws.entity.SaleEntity;
import com.ws.entity.dto.SaleDto;
import com.ws.mapper.ISaleMapper;
import com.ws.repository.SaleRepository;
import com.ws.service.ISaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Optional;

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
                .map(s -> {
                    s.setSaleCod(setCode(s.getId().toString()));
                    return saleRepository.save(s);
                }).map(mapper::toDto);
    }

    @Override
    public Mono<SaleDto> findById(Long id) {

        Optional<SaleEntity>  sale = saleRepository.findById(id);
        if(sale.isPresent())
            return Mono.fromCallable(() -> sale.get())
                    .map(mapper::toDto);

        return Mono.empty();
    }


    private String setCode(String id){

        int limit = 5;
        int i = id.length();

        String cod = "";
        for(int c = i ; c < limit ; c++){

            cod+="0";
        }
        return cod+id;

    }
}
