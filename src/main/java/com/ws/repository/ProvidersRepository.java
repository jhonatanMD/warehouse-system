package com.ws.repository;

import com.ws.entity.BrandEntity;
import com.ws.entity.ProvidersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProvidersRepository extends JpaRepository<ProvidersEntity, Long> {

    List<ProvidersEntity> findByCompany_Id(Long id);
}
