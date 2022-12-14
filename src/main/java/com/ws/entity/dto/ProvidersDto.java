package com.ws.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProvidersDto {

    private Long id;
    private String name;
    private String description;
    private String ruc;
    private Boolean status = true;

    private  CompanyDto company;
    private  ProductDto product;

    private List<SupplierContactDto> supplierContacts;
}
