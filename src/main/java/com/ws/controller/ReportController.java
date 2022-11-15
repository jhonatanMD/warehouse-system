package com.ws.controller;

import com.ws.entity.dto.CompanyDto;
import com.ws.entity.dto.HeadquartersDto;
import com.ws.entity.dto.SaleDto;
import com.ws.service.IReportService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final IReportService reportService;

    @GetMapping(value = "/generarReporteOrdenCompra/{id}")
    public Mono<String> generarReporteOrdenCompra(@PathVariable("id") Long id , HttpServletRequest request, HttpServletResponse response){


        return reportService.reportInvoice(id)
                .map(exporter -> {
                    try {
                        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
                        response.setHeader("Content-Disposition", "attachment;filename=boleta-compra.pdf");
                        response.setContentType("application/octet-stream");
                        exporter.exportReport();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    return "LISTO!";
                });


    }


    @PostMapping(value = "/generarProforma")
    public Mono<String> generarProforma(HttpServletResponse response , @RequestBody SaleDto sale,@RequestAttribute("ruc") String ruc){

        sale.setHeadquarters(HeadquartersDto.builder().company(CompanyDto.builder().rut(ruc).build()).build());

        return reportService.reportBoleta(sale)
                .map(exporter -> {
                    try {
                        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
                        response.setHeader("Content-Disposition", "attachment;filename=proforma.pdf");
                        response.setContentType("application/octet-stream");
                        exporter.exportReport();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    return "LISTO!";
                });


    }
}
