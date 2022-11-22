package com.ws.service.impl;

import com.ws.entity.dto.SaleDetailDto;
import com.ws.entity.dto.SaleDto;
import com.ws.entity.dto.SaleReportDto;
import com.ws.service.IReportService;
import com.ws.service.ISaleService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

import static com.ws.util.Util.percentage;

@Service
@RequiredArgsConstructor
public class ReportService implements IReportService {

    private final ISaleService saleService;
    @Override
    public Mono<JRPdfExporter> reportInvoice(Long saleId) {

       return saleService.findById(saleId)
                .map(sale -> generarProformaBoleta(sale,"/report/boleta-venta.jrxml"));

    }
    @Override
    public Mono<JRPdfExporter> reportBoleta(SaleDto sale) {

        sale.setSaleDetails(sale.getSaleDetails().stream().map(r -> {
            r.setPTotal(r.getPriceUnit().multiply(BigDecimal.valueOf(r.getAmount())).setScale(2, RoundingMode.FLOOR));
            return r;
        }).collect(Collectors.toList()));

        BigDecimal total = BigDecimal.valueOf(sale.getSaleDetails().stream().map(SaleDetailDto::getPTotal).mapToDouble(BigDecimal::doubleValue).sum()).setScale(2,RoundingMode.FLOOR);

        sale.setPTotal(total);
        sale.setPIgv(percentage(sale.getPTotal(),BigDecimal.valueOf(18)).setScale(2,RoundingMode.FLOOR));
        sale.setPSubTotal(sale.getPTotal().subtract(sale.getPIgv()).setScale(2,RoundingMode.FLOOR));


        return Mono.fromCallable(() -> generarProformaBoleta(sale,"/report/proforma-venta.jrxml"));
    }

    @Override
    public Mono<JRPdfExporter> findBySaleByDate(String d1, String d2, Long headquarters,String ruc) {


        List<SaleDto> saleDtos = saleService.findBySaleByDate(d1, d2, headquarters);

        BigDecimal total =  BigDecimal.valueOf(saleDtos.stream().map(SaleDto::getPTotal).mapToDouble(BigDecimal::doubleValue).sum()).setScale(2,RoundingMode.FLOOR);

        return Mono.fromCallable(() -> generarReporteFechas(saleDtos,"/report/reporte-venta-fechas.jrxml",ruc,d1,d2,total));
    }


    private JRPdfExporter generarProformaBoleta(SaleDto sale, String file){
            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(sale.getSaleDetails().stream()
                    .map(detail ->   new SaleReportDto.ProductSaleDto(detail.getAmount().toString(),
                            detail.getProductDetail(), detail.getPriceUnit().toString(),detail.getPTotal().toString())).collect(Collectors.toList()),false);
            Map<String, Object> parameters = new HashMap<String, Object>();


            parameters.put("ruc-empresa",sale.getSrs());
            parameters.put("ruc",sale.getHeadquarters().getCompany().getRut());
            parameters.put("cod-boleta",sale.getSaleCod());
            parameters.put("direccion-empresa",sale.getAddress());
            parameters.put("numero-serie","");
            parameters.put("area-responsable",sale.getPayCondition());
            parameters.put("apellido-nombre",sale.getReferralGuide());
            parameters.put("dni",sale.getCustomerDoc());
            parameters.put("fecha", LocalDate.now().toString());
            parameters.put("dia", String.valueOf(LocalDate.now().getDayOfMonth()));
            parameters.put("mes", LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")));
            parameters.put("ano", String.valueOf(LocalDate.now().getYear()));
            parameters.put("subTotal", "S/ " + sale.getPSubTotal());
            parameters.put("igv", "S/ " + sale.getPIgv());
            parameters.put("total", "S/ " + sale.getPTotal());



            final InputStream stream = this.getClass().getResourceAsStream(file);
            JasperReport archivo = null;
            try {
                archivo = JasperCompileManager.compileReport(stream);
            } catch (JRException e) {
                throw new RuntimeException(e);
            }
            JasperPrint jasperPrint = null;
            try {
                jasperPrint = JasperFillManager.fillReport(archivo,parameters,beanColDataSource);
            } catch (JRException e) {
                throw new RuntimeException(e);
            }

            JRPdfExporter exporter = new JRPdfExporter();
            SimplePdfReportConfiguration reportConfigPDF = new SimplePdfReportConfiguration();
            exporter.setConfiguration(reportConfigPDF);
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

            return exporter;

        }

    private JRPdfExporter generarReporteFechas(List<SaleDto> sales, String file , String ruc,String d1, String d2,BigDecimal total){
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(sales.stream()
                .map(detail ->   new SaleReportDto.ProductSaleDto(detail.getSaleCod(),
                        detail.getCustomerDoc() + " - " + detail.getSrs() , null,detail.getPTotal().toString())).collect(Collectors.toList()),false);
        Map<String, Object> parameters = new HashMap<String, Object>();


        parameters.put("ruc-empresa",d1);
        parameters.put("fecha", d2);
        parameters.put("ruc",ruc);
        parameters.put("total", "S/ " +total);



        final InputStream stream = this.getClass().getResourceAsStream(file);
        JasperReport archivo = null;
        try {
            archivo = JasperCompileManager.compileReport(stream);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport(archivo,parameters,beanColDataSource);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }

        JRPdfExporter exporter = new JRPdfExporter();
        SimplePdfReportConfiguration reportConfigPDF = new SimplePdfReportConfiguration();
        exporter.setConfiguration(reportConfigPDF);
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

        return exporter;

    }

}
