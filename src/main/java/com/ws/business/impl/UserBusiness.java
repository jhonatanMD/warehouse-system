package com.ws.business.impl;

import com.ws.business.IUserBusiness;
import com.ws.entity.dto.UserDto;
import com.ws.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserBusiness implements IUserBusiness {


    private final IUserService userService;

    @Override
    public Mono<UserDto> createUser(UserDto user, Long company) {
        return userService.getUser(user.getUser(),company)
                .filter(res -> res.equals(Boolean.FALSE))
                .flatMap(res -> userService.save(user))
                .switchIfEmpty( Mono.error(new ResponseStatusException(HttpStatus.CONFLICT,"Username ya existente , intente con otro nombre")));
    }
}
