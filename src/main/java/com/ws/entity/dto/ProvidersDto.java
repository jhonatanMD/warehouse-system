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

    private HeadquartersDto headquarters;

    private List<SupplierContactDto> supplierContacts;
}
