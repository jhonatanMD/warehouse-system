package com.ws.service.impl;

import com.ws.entity.dto.UserDto;
import com.ws.mapper.IUserMapper;
import com.ws.repository.UserRepository;
import com.ws.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final IUserMapper mapper;


    @Override
    public Flux<UserDto> findAll() {
        return Flux.fromIterable(userRepository.findAll())
                .map(mapper::toDto);
    }

    @Override
    public Mono<UserDto> save(UserDto userDto) {
        return Mono.fromCallable(() -> userRepository.save(mapper.toEntity(userDto)))
                .map(mapper::toDto);
    }

    @Override
    public Mono<UserDto> update(UserDto userDto, Long id) {

        userDto.setId(id);

        return Mono.fromCallable(() -> userRepository.save(mapper.toEntity(userDto)))
                .map(mapper::toDto);
    }

    @Override
    public Mono<UserDto> getLogin(String userName, String password) {
        return Mono.fromCallable(() -> userRepository.findByUserAndPasswordAndStatusIsTrue(userName,password))
                .map(mapper::toDto);
    }

    @Override
    public Mono<Boolean> getUser(String userName, Long company) {
        return Mono.fromCallable(() -> userRepository.findByUserAndEmployee_Headquarters_Company_Id(userName,company).isPresent());
    }
}
