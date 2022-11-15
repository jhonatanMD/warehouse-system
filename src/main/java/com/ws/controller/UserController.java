package com.ws.controller;

import com.ws.business.IUserBusiness;
import com.ws.entity.dto.UserDto;
import com.ws.entity.dto.data.UserRequest;
import com.ws.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;
    private final IUserBusiness userBusiness;


    @GetMapping()
    public Flux<UserDto> getAll(){
        return userService.findAll();
    }

    @PostMapping()
    public Mono<UserDto> save(@RequestAttribute("company") Long company, @RequestBody UserRequest userDto){


        return userBusiness.createUser(userDto,company);
    }

    @PutMapping("/{id}")
    public Mono<UserDto> update(@PathVariable Long id, @RequestBody UserRequest userDto){
        return userService.update(userDto,id);
    }

    @GetMapping("/{id}")
    public Mono<UserDto> getById(@PathVariable Long id){
        return userService.findById(id);
    }
}
