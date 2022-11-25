package com.ws.repository;

import com.ws.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query(nativeQuery = true ,value = "SELECT * FROM product p inner join store_headquarters sh on p.store_id = sh.store_entity_id \n" +
            "where sh.headquarters_id = ?1")
    List<ProductEntity> findByStoreHeaders(Long id);


    @Query(value = "SELECT c FROM ProductEntity c where c.headquarters.id =?1 and c.miniCode =?2 and c.store.id =?3 and c.brand.id=?4 and c.category.id=?5 and c.material.id=?6 and c.type.id=?7")
    List<ProductEntity> findProduct(Long id,String miniCode,Long idStorage,Long brand,Long category,Long material,Long type);
    List<ProductEntity> findByStore_Id(Long id);

    @Query(value = "SELECT c FROM ProductEntity c  where c.headquarters.id =?1 and c.stock < ?2 and c.status is true ")
    List<ProductEntity> findProductStock(Long id,Long stock );
}
