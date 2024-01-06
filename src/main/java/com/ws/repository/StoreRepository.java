package com.ws.repository;

import com.ws.entity.CategoryEntity;
import com.ws.entity.MaterialEntity;
import com.ws.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<StoreEntity,Long> {

    @Query(value = "SELECT s.* FROM store s inner join store_headquarters sh on s.id = sh.store_entity_id where sh.headquarters_id = ?1 " , nativeQuery = true)
    List<StoreEntity> findStoreByHeadQuarters(Long id);

    @Query(value = "SELECT s.* FROM store s inner join store_headquarters sh on s.id = sh.store_entity_id where sh.headquarters_id = :id and TRIM(UPPER(s.name)) LIKE TRIM(UPPER(:name))" , nativeQuery = true)
    Optional<StoreEntity> findByHeadquartersIdAndName(@Param("id")Long id , @Param("name") String name);
}
