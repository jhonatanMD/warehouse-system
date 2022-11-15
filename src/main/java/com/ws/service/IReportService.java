package com.ws.service;

import com.ws.entity.dto.SaleDto;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import reactor.core.publisher.Mono;

public interface IReportService {

    Mono<JRPdfExporter> reportInvoice(Long saleId);
    Mono<JRPdfExporter> reportBoleta(SaleDto saleDto);
}
