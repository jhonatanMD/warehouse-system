package com.ws.repository;

import com.ws.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByHeadquarters_Id(Long id);
    List<ProductEntity> findByStore_Id(Long id);
}
