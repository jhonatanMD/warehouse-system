package com.ws.controller;

import com.ws.entity.dto.HeadquartersDto;
import com.ws.entity.dto.PermissionRoleDto;
import com.ws.entity.dto.PermissionRoleResponseDto;
import com.ws.service.IPermissionRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/permission-role")
@RequiredArgsConstructor
public class PermissionRoleController {

    private final IPermissionRoleService permissionRoleService;;

    @GetMapping()
    public Flux<PermissionRoleDto> get(){
        return permissionRoleService.findAll();
    }

    @PostMapping()
    public Mono<PermissionRoleDto> save(@RequestBody PermissionRoleDto permissionRoleDto){
        return permissionRoleService.save(permissionRoleDto);
    }

    @GetMapping("/role/{role}")
    public Flux<PermissionRoleResponseDto> findByRole(@PathVariable Long role){
        return permissionRoleService.findByRol(role);
    }
}
