package com.ws.repository;

import com.ws.entity.SaleHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleHistoryRepository extends JpaRepository<SaleHistoryEntity,Long> {
}
