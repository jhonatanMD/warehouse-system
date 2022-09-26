package com.ws.controller;

import com.ws.entity.ModuleEntity;
import com.ws.repository.ModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/module")
@RequiredArgsConstructor
public class ModuleController {

    private final ModuleRepository repository;


    @PostMapping()
    public ModuleEntity save(@RequestBody ModuleEntity moduleEntity){
        return repository.save(moduleEntity);
    }
}
