package com.sumanta.saleService.service;

import com.sumanta.saleService.entities.SalesEntity;

import java.util.List;

public interface SaleService {

    SalesEntity createSalesRecords(SalesEntity salesEntity);

    List<SalesEntity> getAll();

}
