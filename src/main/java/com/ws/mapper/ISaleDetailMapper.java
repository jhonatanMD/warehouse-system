package com.ws.mapper;

import com.ws.entity.SaleDetailEntity;
import com.ws.entity.dto.SaleDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = {IHeadquartersMapper.class , IProductMapper.class})
public interface ISaleDetailMapper {

    @Mapping(source = "product.id",target = "productId")
    SaleDetailDto toDto(SaleDetailEntity saleDetail);

    @Mapping(target = "product.id",source = "productId")
    SaleDetailEntity toEntity(SaleDetailDto saleDetailDto);
}
