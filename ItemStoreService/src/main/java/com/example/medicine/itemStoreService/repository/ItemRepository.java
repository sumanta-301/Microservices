package com.example.medicine.itemStoreService.repository;

import com.example.medicine.itemStoreService.entity.ItemDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemDetails, Long> {
    ItemDetails findByMedicineNameAndBatchNumber(String medicineName, String batchNumber);
    ItemDetails findByMedicineNameAndRetailerName(String medicineName, String retailerName);
    ItemDetails findByMedicineNameAndRetailerNameAndBatchNumber(String medicineName, String retailerName, String batchNumber);
}

