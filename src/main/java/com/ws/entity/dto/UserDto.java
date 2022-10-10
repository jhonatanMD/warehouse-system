package com.ws.entity.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDto {

    private Long id;
    private String user;
    private String password;
    private Boolean status = true;
    private EmployeeDto employee;
    private Set<RoleDto> role;
}
