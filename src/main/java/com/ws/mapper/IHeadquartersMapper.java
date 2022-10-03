package com.ws.mapper;

import com.ws.entity.HeadquartersEntity;
import com.ws.entity.dto.HeadquartersDto;
import org.mapstruct.Mapper;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring" , uses = ICompanyMapper.class)
public interface IHeadquartersMapper {

    HeadquartersDto toDto(HeadquartersEntity headquartersEntity);
    HeadquartersEntity toEntity(HeadquartersDto headquartersDto);

    default Set<HeadquartersEntity> setHeadquarters(Set<Long> ids){
        return ids.stream().map(id -> {
            HeadquartersEntity headquarters = new HeadquartersEntity();
            headquarters.setId(id);
            return headquarters;
        }).collect(Collectors.toSet());
    }
}
