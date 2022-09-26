package com.ws.entity.dto;

import lombok.Data;

import java.util.Set;

@Data
public class StoreDto {

    private Long id;
    private String name;
    private Boolean status = true;
    private Set<HeadquartersDto> headquarters;
}
