package com.ws.business.impl;

import com.ws.business.IPermissionRoleBusiness;
import com.ws.entity.dto.PermissionRoleDto;
import com.ws.entity.dto.data.PermissionRoleRequest;
import com.ws.service.IModuleService;
import com.ws.service.IPermissionRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionRoleBusiness implements IPermissionRoleBusiness {

    private final IPermissionRoleService permissionRoleService;
    private final IModuleService moduleService;

    @Override
    public Flux<PermissionRoleDto> save(List<PermissionRoleRequest> permissionRoleDto, Long role) {


        return null;
    }
}
