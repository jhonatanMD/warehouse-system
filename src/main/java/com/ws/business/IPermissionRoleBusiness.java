package com.ws.business;

import com.ws.entity.dto.PermissionRoleDto;
import com.ws.entity.dto.data.PermissionRoleRequest;
import reactor.core.publisher.Flux;

import java.util.List;

public interface IPermissionRoleBusiness {

    Flux<PermissionRoleDto> save( List<PermissionRoleRequest> permissionRoleDto , Long rol);
}
