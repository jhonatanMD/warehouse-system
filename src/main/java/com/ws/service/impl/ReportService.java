package com.ws.service.impl;

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
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

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
    public Mono<JRPdfExporter> reportBoleta(SaleDto saleDto) {
        return Mono.fromCallable(() -> generarProformaBoleta(saleDto,"/report/proforma-venta.jrxml"));
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

}
