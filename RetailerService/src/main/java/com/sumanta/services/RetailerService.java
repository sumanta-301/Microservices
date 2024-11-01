package com.sumanta.services;

import com.sumanta.entities.Retailer;

import java.util.List;

public interface RetailerService {
    Retailer createRetailer(Retailer retailer);

    List<Retailer> getAllRetailer();
    Retailer  getRetailerByRetailerId(String retailerId);
    List<Retailer> getRetailerByUserId(String userId);
    List<Retailer> getRetailerByRetailerName(String retailerName);

}
