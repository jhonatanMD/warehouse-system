package com.ws.mapper;

import com.ws.entity.SupplierContactEntity;
import com.ws.entity.dto.SupplierContactDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" )
public interface ISupplierContactMapper {

    SupplierContactDto toDto(SupplierContactEntity supplierContactEntity);
    SupplierContactEntity toEntity(SupplierContactDto supplierContactDto);
}
