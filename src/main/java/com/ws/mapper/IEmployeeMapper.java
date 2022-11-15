package com.ws.mapper;

import com.ws.entity.EmployeeEntity;
import com.ws.entity.dto.EmployeeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" , uses = IHeadquartersMapper.class)
public interface IEmployeeMapper {

    EmployeeDto toDto(EmployeeEntity employeeEntity);
    EmployeeEntity toEntity(EmployeeDto employeeDto);
    EmployeeEntity toEntity(Long id);
}
