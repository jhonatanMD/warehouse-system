package com.ws.repository;

import com.ws.entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TypeRepository extends JpaRepository<TypeEntity, Long> {

    List<TypeEntity> findByHeadquarters_Id(Long id);


    @Query(value = "SELECT t.* FROM Type t WHERE t.headquarters_id = :id AND TRIM(UPPER(t.name)) LIKE TRIM(UPPER(:name))" , nativeQuery = true)
    Optional<TypeEntity> findByHeadquartersIdAndName(@Param("id") Long id , @Param("name")String name);
}
