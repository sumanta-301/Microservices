package com.sumanta.controller;

import com.sumanta.entities.Retailer;
import com.sumanta.services.RetailerService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/retailers")
public class RetailerController {
    private final RetailerService retailerService;

    public RetailerController(RetailerService retailerService) {
        this.retailerService = retailerService;
    }
    @PostMapping
    public ResponseEntity<Retailer> create(@RequestBody Retailer retailer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(retailerService.createRetailer(retailer));

    }@GetMapping
    public ResponseEntity <List<Retailer>> getRetailer() {
        return ResponseEntity.ok(retailerService.getAllRetailer());
    }

    @GetMapping("/{retailerId}")
    public ResponseEntity<Retailer> getRetailerById(@PathVariable String retailerId) {
        return ResponseEntity.ok(retailerService.getRetailerByRetailerId(retailerId));
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Retailer>> getRetailerByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(retailerService.getRetailerByUserId(userId));
    }
    @GetMapping("/{retailerName}")
    public ResponseEntity<List<Retailer>> getRetailerByRetailerName(@PathVariable String retailerName) {
        return ResponseEntity.ok(retailerService.getRetailerByRetailerName(retailerName));

    }

}
