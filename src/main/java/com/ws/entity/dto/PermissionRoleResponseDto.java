package com.ws.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionRoleResponseDto {

    private Long id;
    private Boolean insert;
    private Boolean consult;
    private Boolean update;
    private Boolean delete;
    private ModuleDto modules;

    public PermissionRoleResponseDto(PermissionRoleDto permissionRoleDto) {
        this.id = permissionRoleDto.getId();
        this.insert = permissionRoleDto.getInsert();
        this.consult = permissionRoleDto.getConsult();
        this.update = permissionRoleDto.getUpdate();
        this.delete = permissionRoleDto.getDelete();
        this.modules = permissionRoleDto.getModules();
    }
}
