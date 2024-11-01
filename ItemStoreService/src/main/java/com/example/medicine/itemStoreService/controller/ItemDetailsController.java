package com.example.medicine.itemStoreService.controller;

import com.example.medicine.itemStoreService.entity.ItemDetails;
import com.example.medicine.itemStoreService.entity.RetailerDetails;
import com.example.medicine.itemStoreService.service.ItemService;
import com.example.medicine.itemStoreService.service.RetailerDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = {"*"}, allowedHeaders ={"*"} )
@RequestMapping("/api/itemDetails")
public class ItemDetailsController {
    private final ItemService itemService;
    private final RetailerDetailsService retailerDetailsService;

    public ItemDetailsController(ItemService itemService, RetailerDetailsService retailerDetailsService) {
        super();
        this.itemService = itemService;
        this.retailerDetailsService = retailerDetailsService;
    }


    @PostMapping("/bulk")
    public ResponseEntity<List<ItemDetails>> saveItems(@RequestBody List<ItemDetails> itemDetailsList){
        if (itemDetailsList.isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
        List<ItemDetails> savedItems = itemService.saveItems(itemDetailsList);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItems);
    }
    @PostMapping("/retailer")
    public ResponseEntity<RetailerDetails> createRetailer(@RequestBody RetailerDetails retailerDetails) {
        if(retailerDetails==null){
            return ResponseEntity.badRequest().body(null);
        }
        RetailerDetails saveItems = retailerDetailsService.createRetailers(retailerDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveItems);
    }
    @GetMapping("/retailerId/{retailerId}")
    public RetailerDetails getByRetailerId(@PathVariable("retailerId") long retailerId) {
    return retailerDetailsService.findByRetailId(retailerId);
    }
    @GetMapping("/retailerName/{retailerName}")
    public RetailerDetails getByRetailerName(@PathVariable("retailerName") String retailerName){
        return retailerDetailsService.findByRetailerName(retailerName);
    }
    @GetMapping("/retailer/all")
    public List<RetailerDetails> getAllRetailer(){
    return retailerDetailsService.findAll();
    }
    @PutMapping("/retailerId/update/{retailerId}")
    public RetailerDetails updateRetailerByretailerId(@PathVariable("retailerId") long retailerId, @RequestBody RetailerDetails retailerDetails){
       return retailerDetailsService.updateByRetailerId(retailerDetails, retailerId);

    }
}
