package com.ws.mapper;

import com.ws.entity.HeadquartersEntity;
import com.ws.entity.dto.HeadquartersDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" , uses = ICompanyMapper.class)
public interface IHeadquartersMapper {

    HeadquartersDto toDto(HeadquartersEntity headquartersEntity);
    HeadquartersEntity toEntity(HeadquartersDto headquartersDto);
}
