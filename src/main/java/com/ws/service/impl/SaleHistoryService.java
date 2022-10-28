package com.ws.service.impl;

import com.ws.entity.dto.SaleHistoryDto;
import com.ws.mapper.ISaleHistoryMapper;
import com.ws.repository.SaleHistoryRepository;
import com.ws.service.ISaleHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class SaleHistoryService implements ISaleHistoryService {

    private final SaleHistoryRepository saleHistoryRepository;
    private final ISaleHistoryMapper mapper;

    @Override
    @Transactional
    public void  save(SaleHistoryDto saleHistory) {
      saleHistoryRepository.save(mapper.toEntity(saleHistory));
    }
}
