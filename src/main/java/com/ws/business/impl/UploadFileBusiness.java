package com.ws.business.impl;

import com.ws.business.IUploadFileBusiness;
import com.ws.component.ReadExcelFactory;
import com.ws.entity.dto.*;
import com.ws.entity.dto.data.ProductData;
import com.ws.entity.dto.upload.UploadExcelDataDto;
import com.ws.entity.dto.upload.ValidateExcelDataDto;
import com.ws.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UploadFileBusiness implements IUploadFileBusiness {

    private final ReadExcelFactory readExcelFactory;
    private final IProductService productService;
    private final IMaterialService materialService;
    private final IBrandService brandService;
    private final ITypeService typeService;
    private final ICategoryService categoryService;
    private final IStoreService storeService;
    @Override
    @Transactional
    public void upload(MultipartFile file , Long headquarters) {

       ValidateExcelDataDto res = readExcelFactory.get(file);

       productService.saveAll(res.getProductData().getData().stream()
               .filter(UploadExcelDataDto::areAllFieldsNotNull)
               .map(data -> {
                   BrandDto brandDto = brandService.findByName(headquarters, data.getBrand().trim());
                   MaterialDto materialDto = materialService.findByName(headquarters, data.getMaterial().trim());
                   TypeDto typeDto = typeService.findByName(headquarters, data.getType().trim());
                   CategoryDto categoryDto = categoryService.findByName(headquarters, data.getCategory().trim());
                   StoreDto storeDto = storeService.findByName(headquarters, data.getStore().trim());

                   return ProductData.builder()
                           .miniCode(data.getCode())
                           .name(data.getName())
                           .brand(brandDto.getId())
                           .material(materialDto.getId())
                           .type(typeDto.getId())
                           .category(categoryDto.getId())
                           .store(storeDto.getId())
                           .costDollars(BigDecimal.ZERO)
                           .costSoles(new BigDecimal(data.getPrice()))
                           .finalCost(new BigDecimal(data.getPrice()))
                           .headquarters(headquarters)
                           .stock(Long.parseLong(data.getStock()))
                           .status(Boolean.TRUE)
                           .build();
               }).collect(Collectors.toList()));
    }
}
