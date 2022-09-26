package com.ws.repository;

import com.ws.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    List<BrandEntity> findByHeadquarters_Id(Long id);

}
