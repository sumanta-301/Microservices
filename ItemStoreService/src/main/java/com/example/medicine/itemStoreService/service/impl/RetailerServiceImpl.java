package com.example.medicine.itemStoreService.service.impl;

import com.example.medicine.itemStoreService.entity.ItemDetails;
import com.example.medicine.itemStoreService.entity.RetailerDetails;
import com.example.medicine.itemStoreService.repository.RetailDetailsRepository;
import com.example.medicine.itemStoreService.service.RetailerDetailsService;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class RetailerServiceImpl implements RetailerDetailsService {
  private final RetailDetailsRepository retailDetailsRepository;

    public RetailerServiceImpl(RetailDetailsRepository retailDetailsRepository) {
        this.retailDetailsRepository = retailDetailsRepository;
    }

    @Override
    public RetailerDetails createRetailers(RetailerDetails retailerDetails) {
       try {
           if(retailerDetails !=null) {
               return retailDetailsRepository.save(retailerDetails);
           }
           throw new IllegalArgumentException("RetailerId can not be empty");

       } catch (Exception e) {
           throw new RuntimeException("Failed to create retailer details");
       }
    }

    @Override
    public RetailerDetails findByRetailId(long retailerId) {
        RetailerDetails retailerDetails = retailDetailsRepository.findByRetailerId(retailerId);
        if(retailerDetails != null) {
            return retailerDetails;
        } else{
            return null;
        }

    }

    @Override
    public List<RetailerDetails> findAll() {
        return retailDetailsRepository.findAll();
    }

    @Override
    public RetailerDetails findByRetailerName(String retailerName) {
        return retailDetailsRepository.findByRetailerName(retailerName);
    }

    @Override
    public RetailerDetails updateByRetailerId(RetailerDetails retailerDetails, long retailerId) {
        RetailerDetails existingId = retailDetailsRepository.findByRetailerId(retailerId);
        System.out.println("inside thr update function");
        try{
            if (existingId != null) {
                existingId.setRetailerName(retailerDetails.getRetailerName());
                existingId.setRetailerAddress(retailerDetails.getRetailerAddress());
                existingId.setRetailerContactNo(retailerDetails.getRetailerContactNo());
                existingId.setRetailerGstNumber(retailerDetails.getRetailerGstNumber());
                retailDetailsRepository.save(existingId);
            } else {
                return null;
            }
        } catch (RuntimeException e) {
            System.out.println("No matching id found"+ e);

        }
        return existingId;
    }

}
