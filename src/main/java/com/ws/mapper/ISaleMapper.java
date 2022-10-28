package com.ws.mapper;

import com.ws.entity.SaleEntity;
import com.ws.entity.dto.SaleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" , uses = {IHeadquartersMapper.class,ISaleDetailMapper.class})
public interface ISaleMapper {

    SaleDto toDto(SaleEntity saleEntity);
    SaleEntity toEntity(SaleDto saleDto);
}
