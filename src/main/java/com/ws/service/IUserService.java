package com.ws.service;

import com.ws.entity.dto.UserDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserService {

    Flux<UserDto> findAll();
    Mono<UserDto> save(UserDto userDto);
    Mono<UserDto> update(UserDto userDto ,Long id);

    Mono<UserDto> getLogin(String userName , String password);

    Mono<Boolean> getUser(String userName,Long company);



}
