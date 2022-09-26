package com.ws.mapper;

import com.ws.entity.ModuleEntity;
import com.ws.entity.dto.ModuleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IModuleMapper {

    ModuleDto toDto(ModuleEntity moduleEntity);
    ModuleEntity toEntity(ModuleDto moduleDto);
}
