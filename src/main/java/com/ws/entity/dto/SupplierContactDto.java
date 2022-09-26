package com.ws.entity.dto;

import lombok.Data;

@Data
public class SupplierContactDto {

    private Long id;
    private String name;
    private String description;
    private String phone;
    private String exhibit;
    private String email;
    private String obs;
    private String web;

    private Boolean status = true;

}
