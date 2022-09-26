package com.ws.service;

import com.ws.entity.dto.EmployeeDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IEmployeeService {

    Flux<EmployeeDto> findAll(Long id);
    Mono<EmployeeDto> save(EmployeeDto employeeDto);
    Mono<EmployeeDto> update(EmployeeDto employeeDto ,Long id);



}
