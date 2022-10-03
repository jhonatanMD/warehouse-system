package com.ws.entity.dto.data;

import com.ws.entity.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private Long id;
    private String user;
    private String password;
    private Boolean status = true;
    private EmployeeDto employee;
    private Set<Long> role;
}
