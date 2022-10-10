package com.ws.mapper;

import com.ws.entity.BrandEntity;
import com.ws.entity.dto.BrandDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" , uses = IHeadquartersMapper.class)
public interface IBrandMapper {

    BrandDto toDto(BrandEntity brandEntity);
    BrandEntity toEntity(BrandDto brandDto);
    BrandEntity toEntity(Long id);
}
