package com.sumanta.inventory.service;

import com.sumanta.inventory.entities.InventoryEntity;

import java.util.List;
import java.util.Optional;

public interface InventoryService {
    InventoryEntity addInventoryItems(InventoryEntity inventory);
    void removeInventoryItems(String batchNumber, String brandName, String retailerId, int quantity );


}
