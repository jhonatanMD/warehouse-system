package com.ws.entity.dto;

import lombok.Data;

@Data
public class PermissionRoleResponseDto {

    private Long id;
    private Boolean insert;
    private Boolean consult;
    private Boolean update;
    private Boolean delete;
    private ModuleDto modules;
}
