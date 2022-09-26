package com.ws.repository;

import com.ws.entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeRepository extends JpaRepository<TypeEntity, Long> {

    List<TypeEntity> findByHeadquarters_Id(Long id);
}
