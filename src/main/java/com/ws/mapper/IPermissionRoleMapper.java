package com.ws.mapper;

import com.ws.entity.PermissionRoleEntity;
import com.ws.entity.dto.PermissionRoleDto;
import com.ws.entity.dto.PermissionRoleResponseDto;
import com.ws.entity.dto.data.PermissionRoleRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = {IRoleMapper.class , IModuleMapper.class})
public interface IPermissionRoleMapper {

    @Mapping(source = "i", target = "insert")
    @Mapping(source = "c", target = "consult")
    @Mapping(source = "u", target = "update")
    @Mapping(source = "d", target = "delete")
    PermissionRoleDto toDto(PermissionRoleEntity permissionRoleEntity);

    @Mapping(source = "i", target = "insert")
    @Mapping(source = "c", target = "consult")
    @Mapping(source = "u", target = "update")
    @Mapping(source = "d", target = "delete")
    PermissionRoleResponseDto toDtoResponse(PermissionRoleEntity permissionRoleEntity);



    @Mapping(target = "i", source = "insert")
    @Mapping(target = "c", source = "consult")
    @Mapping(target = "u", source = "update")
    @Mapping(target = "d", source = "delete")
    PermissionRoleEntity toEntity(PermissionRoleDto permissionRoleDto);


    @Mapping(target = "i", source = "insert")
    @Mapping(target = "c", source = "consult")
    @Mapping(target = "u", source = "update")
    @Mapping(target = "d", source = "delete")
    @Mapping(target = "modules", source = "module")
    PermissionRoleEntity toEntity(PermissionRoleRequest permissionRoleRequest);
}
