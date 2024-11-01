package com.example.medicine.itemStoreService.service;

import com.example.medicine.itemStoreService.entity.ItemDetails;
import com.example.medicine.itemStoreService.entity.RetailerDetails;

import java.util.List;

public interface RetailerDetailsService {
    public RetailerDetails createRetailers(RetailerDetails retailerDetails);
    RetailerDetails findByRetailId(long  retailerId);
    List<RetailerDetails> findAll();
    RetailerDetails findByRetailerName(String retailerName);
    RetailerDetails updateByRetailerId(RetailerDetails retailerDetails, long retailerId);

}
