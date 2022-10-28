package com.ws.mapper;

import com.ws.entity.CategoryEntity;
import com.ws.entity.dto.CategoryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" , uses = IHeadquartersMapper.class)
public interface ICategoryMapper {

    CategoryDto toDto(CategoryEntity categoryEntity);
    CategoryEntity toEntity(CategoryDto categoryDto);
    CategoryEntity toEntity(Long id);
}
