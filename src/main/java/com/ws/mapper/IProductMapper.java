package com.ws.mapper;

import com.ws.entity.ProductEntity;
import com.ws.entity.dto.ProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" , uses = {IHeadquartersMapper.class , ICategoryMapper.class , ITypeMapper.class , IStoreMapper.class , IMaterialMapper.class , IBrandMapper.class})
public interface IProductMapper {

    ProductDto toDto(ProductEntity productEntity);
    ProductEntity toEntity(ProductDto productDto);
}
