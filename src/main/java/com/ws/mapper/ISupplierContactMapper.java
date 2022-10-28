package com.ws.mapper;

import com.ws.entity.SupplierContactEntity;
import com.ws.entity.dto.SupplierContactDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring" )
public interface ISupplierContactMapper {

    SupplierContactDto toDto(SupplierContactEntity supplierContactEntity);
    SupplierContactEntity toEntity(SupplierContactDto supplierContactDto);
    SupplierContactEntity toEntity(Long id);
    List<SupplierContactEntity> toEntity(List<Long> id);
}
