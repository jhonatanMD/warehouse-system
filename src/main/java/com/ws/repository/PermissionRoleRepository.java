package com.ws.repository;

import com.ws.entity.PermissionRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionRoleRepository extends JpaRepository<PermissionRoleEntity , Long> {


    @Query(value = "select c from PermissionRoleEntity  c where c.role.id = ?1")
    List<PermissionRoleEntity> findByRoleId(Long id);

}
