package com.ws.controller;

import com.ws.entity.dto.ModuleDto;
import com.ws.service.IModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/module")
@RequiredArgsConstructor
public class ModuleController {

    private final IModuleService moduleService;

    @PostMapping()
    public Mono<ModuleDto> save(@RequestBody ModuleDto moduleDto){
        return moduleService.save(moduleDto);
    }
    @GetMapping()
    public Flux<ModuleDto> getModules(){
        return moduleService.findAll();
    }
}
