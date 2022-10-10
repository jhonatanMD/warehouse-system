package com.ws.entity.dto.data;

import com.ws.entity.dto.CompanyDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResponse {
    private Long id;
    private String name;

    public  CompanyResponse(CompanyDto companyDto){
        this.id = companyDto.getId();
        this.name = companyDto.getName();
    }
}
