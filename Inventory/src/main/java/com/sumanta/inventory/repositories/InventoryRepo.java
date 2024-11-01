package com.sumanta.inventory.repositories;

import com.sumanta.inventory.entities.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepo extends JpaRepository<InventoryEntity, Integer> {

    Optional<InventoryEntity> findAllByBrandNameAndBatchNumber(String brandName, String batchNumber);
    InventoryEntity findByBrandNameAndBatchNumberAndRetailerId(String brandName, String batchNumber, String RetailerId);

}
