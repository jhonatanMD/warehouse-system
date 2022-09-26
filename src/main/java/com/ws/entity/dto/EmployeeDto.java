package com.ws.entity.dto;

import lombok.Data;

@Data
public class EmployeeDto {

    private Long id;
    private String name;
    private String lastName;
    private String secondSurName;
    private Boolean status = true;
    private HeadquartersDto headquarters;
}
