package com.example.medicine.itemStoreService.controller;

import com.example.medicine.itemStoreService.entity.ItemDetails;
import com.example.medicine.itemStoreService.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = {"*"}, allowedHeaders ={"*"} )
@RequestMapping("/api/itemDetails")
public class ItemDetailsController {
    private final ItemService itemService;

    public ItemDetailsController(ItemService itemService) {
        super();
        this.itemService = itemService;
    }
    @PostMapping("/bulk")
    public ResponseEntity<List<ItemDetails>> saveItems(@RequestBody List<ItemDetails> itemDetailsList){
        if (itemDetailsList.isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
        List<ItemDetails> savedItems = itemService.saveItems(itemDetailsList);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItems);
    }
}
