package com.ws.mapper;

import com.ws.entity.RoleEntity;
import com.ws.entity.dto.RoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring" , uses = IHeadquartersMapper.class)
public interface IRoleMapper {

    RoleDto toDto(RoleEntity roleEntity);
    RoleEntity toEntity(RoleDto roleDto);

    @Mapping(target = "id" , source = "role")
    RoleEntity toEntity(Long role);

    default Set<RoleEntity> setRole(Set<Long> ids){
        return ids.stream().map(id -> {
            RoleEntity role = new RoleEntity();
            role.setId(id);
            return role;
        }).collect(Collectors.toSet());
    }
}
