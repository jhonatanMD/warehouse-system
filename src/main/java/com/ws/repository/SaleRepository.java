package com.ws.repository;

import com.ws.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SaleRepository extends JpaRepository<SaleEntity,Long> {

    @Query(value = "select month(created_date) , count(*) from sale where YEAR(created_date) = :year and headquarters_id= :headquarters  group by month(created_date)",nativeQuery = true)
    List<long[]> findByCountSaleByDate(@Param("year") int year,@Param("headquarters") Long headquarters);


    @Query(value = "select * from sale where date(created_date) between :d1 and :d2 and headquarters_id = :headquarters",nativeQuery = true)
    List<SaleEntity> findBySaleByDate(@Param("d1") String d1,@Param("d2") String d2,@Param("headquarters") Long headquarters);
}
