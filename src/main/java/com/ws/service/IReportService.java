package com.ws.service;

import net.sf.jasperreports.engine.export.JRPdfExporter;
import reactor.core.publisher.Mono;

public interface IReportService {

    Mono<JRPdfExporter> reportInvoice();
}
