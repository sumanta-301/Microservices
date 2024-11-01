package com.sumanta.inventory.service.impl;

import com.sumanta.inventory.entities.InventoryEntity;
import com.sumanta.inventory.repositories.InventoryRepo;
import com.sumanta.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class InventoryServiceImpl implements InventoryService {
    @Autowired

  private InventoryRepo inventoryRepo;

    @Override
    public InventoryEntity addInventoryItems(InventoryEntity inventory) {
        return inventoryRepo.save(inventory);
    }

    @Override
    public void removeInventoryItems(String batchNumber, String brandName, String retailerId, int quantity) {
        InventoryEntity foundedItems = inventoryRepo.findByBrandNameAndBatchNumberAndRetailerId(batchNumber, brandName, retailerId);

        if (foundedItems == null) {
            System.out.println("No inventory found for the given criteria" + batchNumber + "retailerId" + retailerId + " brandName" + brandName);
        } else {
            Integer availableQuantity = foundedItems.getQuantity();
            foundedItems.setQuantity(availableQuantity- quantity);
            inventoryRepo.saveAndFlush(foundedItems);
        }

    }


}
