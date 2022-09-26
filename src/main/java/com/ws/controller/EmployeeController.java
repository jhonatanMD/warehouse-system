package com.ws.controller;

import com.ws.entity.dto.EmployeeDto;
import com.ws.entity.dto.HeadquartersDto;
import com.ws.entity.dto.RoleDto;
import com.ws.service.IEmployeeService;
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
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final IEmployeeService employeeService;


    @GetMapping()
    public Flux<EmployeeDto> getAll(@RequestAttribute("headquarters") Long headquarters){
        return employeeService.findAll(headquarters);
    }

    @PostMapping()
    public Mono<EmployeeDto> save(@RequestAttribute("headquarters") Long headquarters, @RequestBody EmployeeDto employeeDto){
        employeeDto.setHeadquarters(HeadquartersDto.builder().id(headquarters).build());
        return employeeService.save(employeeDto);
    }
}
