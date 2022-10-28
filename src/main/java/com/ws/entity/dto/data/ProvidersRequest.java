package com.ws.entity.dto.data;

import com.ws.entity.dto.CompanyDto;
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
    private CompanyDto company;

    private List<Long> supplierContacts;
}
