package com.ws.repository;

import com.ws.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    List<CategoryEntity> findByHeadquarters_Id(Long id);


    @Query(value = "SELECT t.* FROM category t WHERE t.headquarters_id = :id AND TRIM(UPPER(t.name)) LIKE TRIM(UPPER(:name))" , nativeQuery = true)
    Optional<CategoryEntity> findByHeadquartersIdAndName(@Param("id") Long id , @Param("name")String name);
}
