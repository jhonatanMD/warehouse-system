package com.ws.mapper;

import com.ws.entity.StoreEntity;
import com.ws.entity.dto.StoreDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" , uses = IHeadquartersMapper.class)
public interface IStoreMapper {

    StoreDto toDto(StoreEntity storeEntity);
    StoreEntity toEntity(StoreDto storeDto);
}
