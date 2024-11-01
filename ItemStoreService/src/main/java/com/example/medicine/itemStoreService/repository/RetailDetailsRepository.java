package com.example.medicine.itemStoreService.repository;

import com.example.medicine.itemStoreService.entity.RetailerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RetailDetailsRepository  extends JpaRepository<RetailerDetails, Long> {
    RetailerDetails findByRetailerId(long  retailerId);
    List<RetailerDetails> findAll();
    RetailerDetails findByRetailerName(String retailerName);

}
