package com.ws.entity.dto.data;

import com.ws.entity.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {

    private String name;
    private String lastName;
    private String secondSurName;

    public EmployeeResponse(EmployeeDto employeeDto){

        this.name = employeeDto.getName();
        this.lastName = employeeDto.getLastName();
        this.secondSurName = employeeDto.getSecondSurName();

    }
}
