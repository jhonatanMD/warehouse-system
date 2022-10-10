package com.ws.mapper;

import com.ws.entity.MaterialEntity;
import com.ws.entity.dto.MaterialDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" , uses = IHeadquartersMapper.class)
public interface IMaterialMapper {

    MaterialDto toDto(MaterialEntity materialEntity);
    MaterialEntity toEntity(MaterialDto materialDto);
    MaterialEntity toEntity(Long id);
}
