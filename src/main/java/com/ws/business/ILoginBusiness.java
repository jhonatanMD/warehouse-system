package com.ws.business;

import com.ws.entity.dto.PermissionRoleResponseDto;
import com.ws.entity.dto.UserDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ILoginBusiness {

    Mono<UserDto> getLogin(String userName , String password);
    Flux<PermissionRoleResponseDto> getPermissionRole(Long idRole);
}
