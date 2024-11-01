package com.sumanta.saleService.service.impl;

import com.sumanta.saleService.entities.SalesEntity;
import com.sumanta.saleService.repo.SaleRepository;
import com.sumanta.saleService.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class SaleServiceImpl implements SaleService {
    @Autowired
    private SaleRepository saleRepository;


    @Override
    public SalesEntity createSalesRecords(SalesEntity salesEntity) {
        return saleRepository.saveAndFlush(salesEntity);
    }

    @Override
    public List<SalesEntity> getAll() {
        return saleRepository.findAll();
    }
}
