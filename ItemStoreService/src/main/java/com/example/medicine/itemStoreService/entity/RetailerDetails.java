package com.example.medicine.itemStoreService.entity;

import jakarta.persistence.*;

@Entity
@Table(name="retailer_details")

public class RetailerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "retailer_id") // Define the column name
    private Long retailerId;
    private String retailerName;
    private String retailerGstNumber;
    private String retailerAddress;
    private String retailerContactNo;

    public RetailerDetails() {
    }

    public RetailerDetails(Long retailerId, String retailerName, String retailerGstNumber, String retailerAddress, String retailerContactNo) {
        this.retailerId = retailerId;
        this.retailerName = retailerName;
        this.retailerGstNumber = retailerGstNumber;
        this.retailerAddress = retailerAddress;
        this.retailerContactNo = retailerContactNo;
    }

    public Long getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(Long retailerId) {
        this.retailerId = retailerId;
    }

    public String getRetailerName() {
        return retailerName;
    }

    public void setRetailerName(String retailerName) {
        this.retailerName = retailerName;
    }

    public String getRetailerGstNumber() {
        return retailerGstNumber;
    }

    public void setRetailerGstNumber(String retailerGstNumber) {
        this.retailerGstNumber = retailerGstNumber;
    }

    public String getRetailerAddress() {
        return retailerAddress;
    }

    public void setRetailerAddress(String retailerAddress) {
        this.retailerAddress = retailerAddress;
    }

    public String getRetailerContactNo() {
        return retailerContactNo;
    }

    public void setRetailerContactNo(String retailerContactNo) {
        this.retailerContactNo = retailerContactNo;
    }
}