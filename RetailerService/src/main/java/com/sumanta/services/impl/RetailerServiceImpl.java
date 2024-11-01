package com.sumanta.services.impl;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sumanta.entities.Retailer;
import com.sumanta.repositories.RetailerRepository;
import com.sumanta.services.RetailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RetailerServiceImpl implements RetailerService {
    @Autowired
   private RetailerRepository retailerRepository;

    @Override
    public Retailer createRetailer(Retailer retailer) {
        String id = UUID.randomUUID().toString();
        retailer.setRetailerId(id);
        return retailerRepository.save(retailer);
    }

    @Override
    public List<Retailer> getAllRetailer() {
        return retailerRepository.findAll();
    }

    @Override
    public Retailer getRetailerByRetailerId(String retailerId) {
        return retailerRepository.findByRetailerId(retailerId);
    }

    @Override
    public List<Retailer> getRetailerByUserId(String userId) {
        List<Retailer> byUserId = retailerRepository.findByUserId(userId);
        return byUserId;
    }

    @Override
    public List<Retailer> getRetailerByRetailerName(String retailerName) {
        return null;
    }
}
