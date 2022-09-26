package com.ws.repository;

import com.ws.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<RoleEntity,Long> {


    List<RoleEntity> findByHeadquarters_Id(Long id);
}
