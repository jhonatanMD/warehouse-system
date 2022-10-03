package com.ws.business;

import com.ws.entity.dto.UserDto;
import com.ws.entity.dto.data.UserRequest;
import reactor.core.publisher.Mono;

public interface IUserBusiness {

    Mono<UserDto> createUser(UserRequest user , Long company);
}
