package com.ws.service.impl;

import com.ws.entity.HeadquartersEntity;
import com.ws.entity.TypeEntity;
import com.ws.entity.dto.TypeDto;
import com.ws.mapper.ITypeMapper;
import com.ws.repository.TypeRepository;
import com.ws.service.ITypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.function.Predicate;

@Slf4j
@Service
@RequiredArgsConstructor
public class TypeService implements ITypeService {


    private final TypeRepository typeRepository;
    private final ITypeMapper mapper;

    @Override
    public Flux<TypeDto> findAll(Long id) {
        return Flux.fromIterable(typeRepository.findByHeadquarters_Id(id))
                //.filter(status::test)
                .map(mapper::toDto);
    }

    @Override
    public Mono<TypeDto> save(TypeDto typeDto) {
        return Mono.fromCallable(() -> typeRepository.save(mapper.toEntity(typeDto)))
                .map(mapper::toDto);
    }

    @Override
    public Mono<TypeDto> update(TypeDto typeDto, Long id) {
        typeDto.setId(id);
        return Mono.fromCallable(() -> typeRepository.save(mapper.toEntity(typeDto)))
                .map(mapper::toDto);
    }

    @Override
    public TypeDto findByName(long id, String name) {

        Optional<TypeEntity> type = typeRepository.findByHeadquartersIdAndName(id,name);

        if(type.isPresent())
            return type.map(mapper::toDto).get();

        return mapper.toDto(typeRepository.save(TypeEntity.builder()
                .name(name)
                .description(name)
                .status(Boolean.TRUE)
                .headquarters(HeadquartersEntity.builder()
                        .id(id)
                        .build())
                .build()));
    }

    private Predicate<TypeEntity> status = p -> p.getStatus();


}
