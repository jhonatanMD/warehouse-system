package com.ws.business.impl;

import com.ws.business.ILoginBusiness;
import com.ws.entity.dto.PermissionRoleResponseDto;
import com.ws.entity.dto.UserDto;
import com.ws.service.IPermissionRoleService;
import com.ws.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class LoginBusiness implements ILoginBusiness {

    private final IUserService userService;
    private final IPermissionRoleService permissionRoleService;

    @Override
    public Mono<UserDto> getLogin(String userName, String password) {
        return userService.getLogin(userName,password);
    }

    @Override
    public Flux<PermissionRoleResponseDto> getPermissionRole(Long idRole) {
        return permissionRoleService.findByRol(idRole);
    }
}
