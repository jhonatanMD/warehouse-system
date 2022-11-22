package com.ws.entity.dto.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtDataResponse {

    private Object jwt;
    private HeadquartersResponse headquarters;
    private CompanyResponse company;
    private EmployeeResponse employee;
    private RoleResponse roles;
}
