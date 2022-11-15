package com.ws.entity.dto.data;

import lombok.Data;

import java.util.List;

@Data
public class ProvidersRequest {

    private Long id;
    private String name;
    private String description;
    private String ruc;
    private Boolean status = true;
    private Long product;
    private Long company;

    private List<Long> supplierContacts;
}
