package com.ws.mapper;

import com.ws.entity.StoreEntity;
import com.ws.entity.dto.StoreDto;
import com.ws.entity.dto.data.StoreRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" , uses = IHeadquartersMapper.class)
public interface IStoreMapper {

    StoreDto toDto(StoreEntity storeEntity);
    StoreEntity toEntity(StoreDto storeDto);
    StoreEntity toEntity(Long id);
    StoreEntity dataToEntity(StoreRequest storeRequest);
}
