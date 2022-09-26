package com.ws.entity.dto;

import lombok.Data;

@Data
public class RoleDto {

    private Long id;
    private String name;
    private Boolean status = true;
    private HeadquartersDto headquarters;
}
