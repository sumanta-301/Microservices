package com.example.medicine.itemStoreService.repository;

import com.example.medicine.itemStoreService.entity.RetailerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetailerRepository extends JpaRepository<RetailerDetails, Long> {
}
