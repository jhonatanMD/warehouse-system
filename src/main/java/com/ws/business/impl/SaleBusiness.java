package com.ws.business.impl;

import com.ws.business.ISaleBusiness;
import com.ws.entity.dto.SaleDetailDto;
import com.ws.entity.dto.SaleDto;
import com.ws.entity.dto.SaleHistoryDto;
import com.ws.entity.dto.data.AddProduct;
import com.ws.service.IProductService;
import com.ws.service.ISaleHistoryService;
import com.ws.service.ISaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleBusiness implements ISaleBusiness {


    private final ISaleService saleService;
    private final ISaleHistoryService saleHistoryService;
    private final IProductService productService;
    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    @Override
    public Flux<SaleDto> findAll(Long headquarters) {
        return saleService.findAll(headquarters);
    }

    @Override
    public Mono<SaleDto> save(SaleDto sale) {

        sale.setSaleDetails(sale.getSaleDetails().stream().map(r -> {
            r.setPTotal(r.getPriceUnit().multiply(BigDecimal.valueOf(r.getAmount())).setScale(2, RoundingMode.FLOOR));
            return r;
        }).collect(Collectors.toList()));

        BigDecimal total = BigDecimal.valueOf(sale.getSaleDetails().stream().map(SaleDetailDto::getPTotal).mapToDouble(BigDecimal::doubleValue).sum()).setScale(2,RoundingMode.FLOOR);

        System.out.println(total);

        sale.setPTotal(total);
        sale.setPIgv(percentage(sale.getPTotal(),BigDecimal.valueOf(18)).setScale(2,RoundingMode.FLOOR));
        sale.setPSubTotal(sale.getPTotal().subtract(sale.getPIgv()).setScale(2,RoundingMode.FLOOR));

        return saleService.save(sale)
                .doOnNext(r -> register(
                        r.getId(),sale.getSaleDetails()));
    }

    public static BigDecimal percentage(BigDecimal base, BigDecimal pct){
        return base.multiply(pct).divide(ONE_HUNDRED);
    }

    private void register(Long id, List<SaleDetailDto> details){
        details.forEach(saleDetailDto -> {

            saleHistoryService.save(SaleHistoryDto.builder().saleId(id).amount(saleDetailDto.getAmount())
                    .productId(saleDetailDto.getProductId()).status(Boolean.TRUE).build());

            productService.addProduct(AddProduct.builder().productId(saleDetailDto.getProductId()).cant(saleDetailDto.getAmount()).build(),"-")
                    .subscribe();

        });
    }
}
