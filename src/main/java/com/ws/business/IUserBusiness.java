package com.ws.business;

import com.ws.entity.dto.UserDto;
import reactor.core.publisher.Mono;

public interface IUserBusiness {

    Mono<UserDto> createUser(UserDto user , Long company);
}
