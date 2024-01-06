package com.ws.entity.dto.data;

import com.ws.entity.dto.PermissionRoleResponseDto;
import com.ws.entity.dto.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponse {
    private Long id;
    private String name;
    private List<PermissionRoleResponseDto> permissionRole;
    public RoleResponse (RoleDto roleDto){
        this.id = roleDto.getId();
        this.name = roleDto.getName();
    }
}
