package com.ws.mapper;

import com.ws.entity.RoleEntity;
import com.ws.entity.dto.RoleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" , uses = IHeadquartersMapper.class)
public interface IRoleMapper {

    RoleDto toDto(RoleEntity roleEntity);
    RoleEntity toEntity(RoleDto roleDto);
}
