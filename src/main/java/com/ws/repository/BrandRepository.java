package com.ws.repository;

import com.ws.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    List<BrandEntity> findByHeadquarters_Id(Long id);


    @Query(value = "SELECT b.* FROM Brand b WHERE b.headquarters_id = :id AND TRIM(UPPER(b.name)) LIKE TRIM(UPPER(:name))" , nativeQuery = true)
    Optional<BrandEntity> findTopByHeadquartersIdAndName(@Param("id") Long id , @Param("name")String name);
}
