package com.example.medicine.itemStoreService.controller;

import com.example.medicine.itemStoreService.entity.RetailerDetails;
import com.example.medicine.itemStoreService.service.RetailerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/retailer")
@RestController

public class RetailerDetailsController {
 private final RetailerService retailerService;

    public RetailerDetailsController(RetailerService retailerService) {
       this.retailerService = retailerService;
 }
//    @PostMapping()
//    public ResponseEntity<RetailerDetails> createRetailer(@RequestBody RetailerDetails retailerDetails){
//        return new ResponseEntity<>(retailerService.createRetailer(retailerDetails), HttpStatus.CREATED);
//
//    }
}
