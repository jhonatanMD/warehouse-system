package com.ws.entity.dto.data;

import com.ws.entity.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtDataResponse {

    private Object jwt;
    private String headquarters;
    private String company;
    private EmployeeResponse employee;
}
