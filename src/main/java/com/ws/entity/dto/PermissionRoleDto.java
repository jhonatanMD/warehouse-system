package com.ws.entity.dto;

import lombok.Data;

import java.util.Set;

@Data
public class PermissionRoleDto {

    private Long id;
    private Boolean insert;
    private Boolean consult;
    private Boolean update;
    private Boolean delete;
    private ModuleDto modules;
    private RoleDto role;
    private Boolean status = true;
}
