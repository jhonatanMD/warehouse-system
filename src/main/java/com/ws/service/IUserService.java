package com.ws.service;

import com.ws.entity.dto.UserDto;
import com.ws.entity.dto.data.UserRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserService {

    Flux<UserDto> findAll();
    Mono<UserDto> save(UserRequest userDto);
    Mono<UserDto> update(UserDto userDto ,Long id);

    Mono<UserDto> getLogin(String userName , String password);

    Mono<Boolean> getUser(String userName,Long company);



}
