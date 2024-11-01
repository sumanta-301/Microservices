package com.example.medicine.itemStoreService.service.impl;

import com.example.medicine.itemStoreService.entity.RetailerDetails;
import com.example.medicine.itemStoreService.repository.RetailerRepository;
import com.example.medicine.itemStoreService.service.RetailerService;
import org.springframework.stereotype.Service;

@Service

public class RetailerServiceImpl implements RetailerService {
    private final RetailerRepository retailerRepository;

    public RetailerServiceImpl(RetailerRepository retailerRepository) {
        super();
        this.retailerRepository = retailerRepository;
    }

    @Override
    public RetailerDetails createRetailer(RetailerDetails retailerDetails) {
         return retailerRepository.save(retailerDetails);

    }

    @Override
    public RetailerDetails updateRetailer(RetailerDetails retailerDetails, long id) {
        return null;
    }
}
