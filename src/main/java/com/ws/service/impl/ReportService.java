package com.ws.service.impl;

import com.ws.service.IReportService;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService implements IReportService {
    @Override
    public Mono<JRPdfExporter> reportInvoice() {
        return Mono.fromCallable(() -> {
            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(Arrays.asList(),false);
            Map<String, Object> parameters = new HashMap<String, Object>();

            /*
            parameters.put("razon-social", ordenCompra.getRazonSocial());
            parameters.put("ruc-empresa",ordenCompra.getRucEmpresa());
            parameters.put("direccion-empresa",ordenCompra.getDireccionEmpresa());
            parameters.put("numero-serie",ordenCompra.getNumeroSerie());
            parameters.put("sres-empresa",ordenCompra.getSresEmpresa());
            parameters.put("ruc-proveedor",ordenCompra.getRucProveedor());
            parameters.put("direccion-proveedor",ordenCompra.getDireccionProveedor());
            parameters.put("fecha",ordenCompra.getFecha());
            parameters.put("email-proveedor",ordenCompra.getEmailProveedor());
            parameters.put("telefono1-proveedor",ordenCompra.getTelefono1Proveedor());
            parameters.put("telefono2-proveedor",ordenCompra.getTelefono2Proveedor());
            parameters.put("atencion-proveedor",ordenCompra.getAtencionProveedor());
            parameters.put("cotizacion-proveedor",ordenCompra.getCotizacionProveedor());
            parameters.put("razon-social", ordenCompra.getRazonSocial());
            parameters.put("tipo-cuenta", ordenCompra.getTipoCuenta());
            parameters.put("requerimiento-proveedor",ordenCompra.getRequerimientosProveedor());
            parameters.put("unidad-proveedor",ordenCompra.getUnidadProveedor());
            parameters.put("motivo-proveedor",ordenCompra.getMotivoProveedor());
            parameters.put("formato-pago",ordenCompra.getFormatoPago());
            parameters.put("plazo-entrega",ordenCompra.getPlazoEntrega());
            parameters.put("numero-cuenta",ordenCompra.getNumeroCuenta());
            parameters.put("subTotal",ordenCompra.getSubTotal());
            parameters.put("igv",ordenCompra.getIgv());
            parameters.put("totalPagar",ordenCompra.getTotalPagar());
            parameters.put("total_texto",ordenCompra.getTotalTexto());
            parameters.put("observacion",ordenCompra.getObservacion());*/

            final InputStream stream = this.getClass().getResourceAsStream("/almacen.jrxml");
            JasperReport archivo = JasperCompileManager.compileReport(stream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(archivo,parameters,beanColDataSource);

            JRPdfExporter exporter = new JRPdfExporter();
            SimplePdfReportConfiguration reportConfigPDF = new SimplePdfReportConfiguration();
            exporter.setConfiguration(reportConfigPDF);
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

            return exporter;
        });
    }
}
