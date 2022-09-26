package com.ws.repository;

import com.ws.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreRepository extends JpaRepository<StoreEntity,Long> {

    @Query(value = "SELECT s.* FROM store s inner join store_headquarters sh on s.id = sh.store_entity_id where sh.headquarters_id = ?1 " , nativeQuery = true)
    List<StoreEntity> findStoreByHeadQuarters(Long id);

}
