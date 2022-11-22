package com.ws.service.impl;

import com.ws.entity.SaleEntity;
import com.ws.entity.dto.SaleDashBoardDto;
import com.ws.entity.dto.SaleDto;
import com.ws.mapper.ISaleMapper;
import com.ws.repository.SaleRepository;
import com.ws.service.ISaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ws.util.Util.getSaleMoth;

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

    @Override
    public Mono<List<SaleDashBoardDto>> findSaleByDate(Long headquarters){

        Map sale = getSaleMoth();

        List<SaleDashBoardDto> sales = new ArrayList<>();
       return Flux.fromIterable(saleRepository.findByCountSaleByDate(LocalDate.now().getYear(),headquarters))
                        .collectMap(d -> d[0], d -> d[1])
                                .map(map -> {
                                    sale.forEach((k,v) -> {
                                        if(map.containsKey(k))
                                            sale.replace(k,map.get(k));
                                    });
                                    return sale;
                                }).map(map -> {

                    map.forEach((k,v) -> {
                        sales.add(SaleDashBoardDto.builder()
                                .moth((long)k)
                                .sales((long)v)
                                .build());
                    });

                    return sales;
                });


    }

    @Override
    public List<SaleDto> findBySaleByDate(String d1, String d2, Long headquarters) {
        return saleRepository.findBySaleByDate(d1,d2,headquarters)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());

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
