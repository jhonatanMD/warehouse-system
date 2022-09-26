package com.ws.entity.dto;

import lombok.Data;

@Data
public class BrandDto {

    private Long id;
    private String name;
    private Boolean status = true;
    private String description;
    private HeadquartersDto headquarters;
}
