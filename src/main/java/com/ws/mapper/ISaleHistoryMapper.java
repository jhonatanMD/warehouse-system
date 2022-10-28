package com.ws.mapper;

import com.ws.entity.SaleHistoryEntity;
import com.ws.entity.dto.SaleHistoryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" , uses = {IProductMapper.class})
public interface ISaleHistoryMapper {


    SaleHistoryDto toDto(SaleHistoryEntity saleHistoryEntity);
    SaleHistoryEntity toEntity(SaleHistoryDto saleHistoryDto);
}
