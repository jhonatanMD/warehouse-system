package com.ws.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleReportDto {


    private String rucEmpresa;
    private String ruc;
    private String codBoleta;
    private String razonSocial;
    private String direccionEmpresa;
    private String numeroSerie;
    private String areaResponsable;
    private String apellido;
    private String dni;
    private String unidad;
    private String motivo;
    private String cliente;
    private String fecha;

    private List<ProductSaleDto> productSale;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductSaleDto{

        private String cantidad;
        private String descripcion;
        private String precio;
        private String total;

    }
}
