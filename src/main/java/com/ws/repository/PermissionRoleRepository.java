package com.ws.repository;

import com.ws.entity.PermissionRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PermissionRoleRepository extends JpaRepository<PermissionRoleEntity , Long> {


    @Query(value = "select c from PermissionRoleEntity  c where c.role.id = ?1 and c.status is true and c.role.status is true ")
    List<PermissionRoleEntity> findByRoleId(Long id);



    Optional<PermissionRoleEntity> findByRole_IdAndModules_Id(Long roleId , Long moduleId);
    Optional<Boolean> findByRole_IdAndModules_IdAndId(Long idRole , Long idModule, Long id);
   /* @Query(value = "DELETE  FROM PermissionRoleEntity  c where c.role.id = ?1 ")
    void deleteByIdRole(Long idRole);*/

   // @Query(value = "call delete_rol(:idRole)",nativeQuery = true)

    @Transactional
    @Modifying
    @Query(value = "delete from PermissionRoleEntity p where p.role.id =?1")
    void deleteByRoleId(Long idRole);

}
