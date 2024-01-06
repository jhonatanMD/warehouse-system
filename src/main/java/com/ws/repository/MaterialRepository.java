package com.ws.repository;

import com.ws.entity.MaterialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MaterialRepository extends JpaRepository<MaterialEntity, Long> {

    List<MaterialEntity> findByHeadquarters_Id(Long id);

    @Query(value = "SELECT m.* FROM Material m WHERE m.headquarters_id = :id AND TRIM(UPPER(m.name)) LIKE TRIM(UPPER(:name))" , nativeQuery = true)
    Optional<MaterialEntity> findByHeadquartersIdAndName(@Param("id") Long id , @Param("name")String name);
}
