package com.ws.config;

import com.ws.repository.BrandRepository;
import com.ws.repository.CategoryRepository;
import com.ws.repository.CompanyRepository;
import com.ws.repository.HeadquartersRepository;
import com.ws.repository.MaterialRepository;
import com.ws.repository.ProductRepository;
import com.ws.repository.RoleRepository;
import com.ws.repository.StoreRepository;
import com.ws.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final CompanyRepository companyRepository;
    private final HeadquartersRepository headquartersRepository;
    private final RoleRepository roleRepository;

    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final MaterialRepository materialRepository;
    private final TypeRepository typeRepository;
    private final StoreRepository storeRepository;

    private final ProductRepository productRepository;



    private void upData(){

    }



}
