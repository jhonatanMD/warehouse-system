package com.ws.controller;

import com.ws.entity.dto.HeadquartersDto;
import com.ws.entity.dto.RoleDto;
import com.ws.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final IRoleService roleService;


    @GetMapping()
    public Flux<RoleDto> getAll(@RequestAttribute("headquarters") Long headquarters){
        return roleService.findAll(headquarters);
    }

    @GetMapping("/{id}")
    public Mono<RoleDto> getById(@PathVariable("id") Long id){
        return roleService.findById(id);
    }

    @PostMapping()
    public Mono<RoleDto> save(@RequestAttribute("headquarters") Long headquarters, @RequestBody RoleDto role){
        role.setHeadquarters(HeadquartersDto.builder().id(headquarters).build());
        return roleService.save(role);
    }

    @PutMapping("/status/{id}")
    public Mono<Boolean> updateStatus(@PathVariable("id") Long id){
        return roleService.updateStatus(id);
    }

    @PutMapping("/{id}")
    public Mono<RoleDto> update(@PathVariable("id") Long id , @RequestBody RoleDto role){
        return roleService.update(role,id);
    }
}
