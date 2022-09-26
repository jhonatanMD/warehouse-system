package com.ws.mapper;

import com.ws.entity.TypeEntity;
import com.ws.entity.dto.TypeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" , uses = IHeadquartersMapper.class)
public interface ITypeMapper {

    TypeDto toDto(TypeEntity typeEntity);
    TypeEntity toEntity(TypeDto typeDto);
}
