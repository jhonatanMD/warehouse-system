package com.ws.controller;

import com.ws.business.IPermissionRoleBusiness;
import com.ws.entity.dto.PermissionRoleDto;
import com.ws.entity.dto.PermissionRoleResponseDto;
import com.ws.entity.dto.data.PermissionRoleRequest;
import com.ws.service.IPermissionRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/permission-role")
@RequiredArgsConstructor
public class PermissionRoleController {

    private final IPermissionRoleService permissionRoleService;
    private final IPermissionRoleBusiness permissionRoleBusiness;

    @GetMapping()
    public Flux<PermissionRoleDto> get(){
        return permissionRoleService.findAll();
    }

    @PostMapping()
    public Mono<PermissionRoleDto> save(@RequestBody PermissionRoleRequest permissionRoleDto){
        return permissionRoleService.save(permissionRoleDto);
    }

    @PostMapping("/all/{role}")
    public Flux<PermissionRoleDto> save(@RequestBody List<PermissionRoleRequest> permissionRoleDto,@PathVariable Long role){
        return permissionRoleService.save(permissionRoleDto,role);
    }

    @GetMapping("/role/{role}")
    public Flux<PermissionRoleResponseDto> findByRole(@PathVariable Long role){
        return permissionRoleService.findByRol(role);
    }

    @PutMapping("/role/{role}")
    public Mono<Boolean> update(@PathVariable Long role,@RequestBody List<PermissionRoleRequest> roles){

        permissionRoleService.delete(role);

        return permissionRoleService.update(roles , role);
    }
}
