package com.ws.service.impl;

import com.ws.entity.dto.EmployeeDto;
import com.ws.mapper.IEmployeeMapper;
import com.ws.repository.EmployeeRepository;
import com.ws.service.IEmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository employeeRepository;
    private final IEmployeeMapper mapper;

    @Override
    public Flux<EmployeeDto> findAll(Long id) {
        return Flux.fromIterable(employeeRepository.findByHeadquarters_Id(id))
                .map(mapper::toDto);
    }

    @Override
    public Mono<EmployeeDto> save(EmployeeDto employeeDto) {
        return Mono.fromCallable(() -> employeeRepository.save(mapper.toEntity(employeeDto)))
                .map(mapper::toDto);
    }

    @Override
    public Mono<EmployeeDto> update(EmployeeDto employeeDto, Long id) {

        employeeDto.setId(id);

        return Mono.fromCallable(() -> employeeRepository.save(mapper.toEntity(employeeDto)))
                .map(mapper::toDto);
    }
}
