package com.ws.controller;

import com.ws.business.IUserBusiness;
import com.ws.entity.dto.UserDto;
import com.ws.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<UserDto> save(@RequestAttribute("company") Long company, @RequestBody UserDto userDto){
        return userBusiness.createUser(userDto,company);
    }
}
