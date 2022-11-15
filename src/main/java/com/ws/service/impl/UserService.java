package com.ws.service.impl;

import com.ws.entity.UserEntity;
import com.ws.entity.dto.UserDto;
import com.ws.entity.dto.data.UserRequest;
import com.ws.mapper.IUserMapper;
import com.ws.repository.UserRepository;
import com.ws.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final IUserMapper mapper;


    @Override
    public Flux<UserDto> findAll() {
        return Flux.fromIterable(userRepository.findAll())
                .filter(status::test)
                .map(mapper::toDto);
    }

    @Override
    public Mono<UserDto> save(UserRequest userDto) {


        UserEntity d = mapper.dataToEntity(userDto);

        return Mono.fromCallable(() -> userRepository.save(mapper.dataToEntity(userDto)))
                .map(mapper::toDto);
    }

    @Override
    public Mono<UserDto> update(UserRequest userDto, Long id) {

        userDto.setId(id);

        return Mono.fromCallable(() -> userRepository.save(mapper.dataToEntity(userDto)))
                .map(mapper::toDto);
    }

    @Override
    public Mono<UserDto> getLogin(String userName, String password) {
        return Mono.fromCallable(() -> userRepository.findByUserAndPasswordAndStatusIsTrue(userName,password))
                .map(mapper::toDto);
    }

    @Override
    public Mono<Boolean> getUser(String userName, Long company) {
        return Mono.fromCallable(() -> userRepository.findByUserAndStatusIsTrueAndEmployee_Headquarters_Company_Id(userName,company).isPresent());
    }

    @Override
    public Mono<UserDto> findById(Long id) {

        Optional<UserEntity> user = userRepository.findById(id);
        if(user.isPresent())
            return Mono.fromCallable(() -> user.get())
                    .map(mapper::toDto);

        return Mono.empty();
    }

    private Predicate<UserEntity> status = p -> p.getStatus();

}
