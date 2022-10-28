package com.ws.mapper;

import com.ws.entity.ProvidersEntity;
import com.ws.entity.dto.ProvidersDto;
import com.ws.entity.dto.data.ProvidersRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" , uses = {IHeadquartersMapper.class ,ISupplierContactMapper.class, ICompanyMapper.class,IProductMapper.class})
public interface IProvidersMapper {

    ProvidersDto toDto(ProvidersEntity providersEntity);

    ProvidersEntity toEntity(ProvidersDto providersDto);
    ProvidersEntity toEntity(ProvidersRequest providersDto);



}
