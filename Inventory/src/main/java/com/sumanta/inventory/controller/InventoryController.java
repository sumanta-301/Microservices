package com.sumanta.inventory.controller;

import com.sumanta.inventory.entities.InventoryEntity;
import com.sumanta.inventory.repositories.InventoryRepo;
import com.sumanta.inventory.service.impl.InventoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryServiceImpl inventoryService;
    @Autowired
    private InventoryRepo inventoryRepo;
    @PostMapping()

    public ResponseEntity<List<InventoryEntity>> createInventoryItems(@RequestBody List<InventoryEntity> inventory) {
        for (InventoryEntity entity : inventory) {
            double cost = entity.getQuantity() * entity.getMedicineCost();
            entity.setTotalCost(cost);
            Optional<InventoryEntity> existingEntity =inventoryRepo.findAllByBrandNameAndBatchNumber(entity.getBrandName(), entity.getBatchNumber());

            if(existingEntity.isPresent()) {
                InventoryEntity existingRecord = existingEntity.get();
               existingRecord.setTotalCost(existingEntity.get().getTotalCost() + cost);
               existingRecord.setQuantity(existingEntity.get().getQuantity() + entity.getQuantity());
               inventoryRepo.save(existingRecord);


            } else {
                inventoryService.addInventoryItems(entity);

            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(inventory);
    }

        @PostMapping("/deductions/{batchNumber}/{retailerId}/{brandName}/{quantity}")
    public ResponseEntity<String> deductQuantity(
            @PathVariable String batchNumber,
           @PathVariable String retailerId,
            @PathVariable  String brandName,
            @PathVariable int quantity
    ) {

        try {
            inventoryService.removeInventoryItems(batchNumber,brandName, retailerId,quantity);
            return ResponseEntity.ok("Quantity Deducted successfully");

        } catch (Exception e ) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some error occurred");

        }

    }
}
