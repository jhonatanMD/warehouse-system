package com.ws.mapper;

import com.ws.entity.ProductEntity;
import com.ws.entity.dto.ProductDto;
import com.ws.entity.dto.data.ProductData;
import com.ws.entity.dto.data.ProductDataResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = {IHeadquartersMapper.class , ICategoryMapper.class , ITypeMapper.class , IStoreMapper.class , IMaterialMapper.class , IBrandMapper.class})
public interface IProductMapper {

    ProductDto toDto(ProductEntity productEntity);

    ProductEntity toEntity(ProductDto productDto);
    ProductEntity toEntity(ProductData productData);
    ProductEntity toEntity(Long id);

    @Mapping(target = "category",source = "category.name")
    @Mapping(target = "type",source = "type.name")
    @Mapping(target = "brand",source = "brand.name")
    @Mapping(target = "material",source = "material.name")
    @Mapping(target = "store",source = "store.name")
    @Mapping(target = "headquarters",source = "headquarters.name")
    ProductDataResponse toData(ProductEntity productEntity);
}
