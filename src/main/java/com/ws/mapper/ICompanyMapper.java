package com.ws.mapper;

import com.ws.entity.CompanyEntity;
import com.ws.entity.dto.CompanyDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" , uses = IHeadquartersMapper.class)
public interface ICompanyMapper {

    CompanyDto toDto(CompanyEntity companyEntity);
    CompanyEntity toEntity(CompanyDto companyDto);
}
