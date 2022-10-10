package com.ws.repository;

import com.ws.entity.HeadquartersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeadquartersRepository extends JpaRepository<HeadquartersEntity, Long> {


    List<HeadquartersEntity> findByCompany_Id(Long id);
}
