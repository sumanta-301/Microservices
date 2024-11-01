package com.example.medicine.itemStoreService.service;

import com.example.medicine.itemStoreService.entity.RetailerDetails;

public interface RetailerService {
    RetailerDetails createRetailer(RetailerDetails retailerDetails);
    RetailerDetails updateRetailer(RetailerDetails retailerDetails, long id);
}
