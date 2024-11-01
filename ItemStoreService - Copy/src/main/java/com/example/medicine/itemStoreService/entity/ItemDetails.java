package com.example.medicine.itemStoreService.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="item_details")
public class ItemDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "retailer_id")
//    private RetailerDetails retailerDetails;

    private String medicineName;
    private String genericName;
    private String companyName;
    private String retailerName;
    private String medicineCategory;
    private String batchNumber;
    private Date manufacturingDate;
    private Date expiryDate;
    private Long medicineQuantity;
    private Double medicineMrp;
    private Double medicineCost;
    private String medicineDesc;

    public ItemDetails() {
    }

    public ItemDetails(Long id, String medicineName, String genericName, String companyName, String retailerName, String medicineCategory, String batchNumber, Date manufacturingDate, Date expiryDate, Long medicineQuantity, Double medicineMrp, Double medicineCost, String medicineDesc) {
        this.id = id;
        this.medicineName = medicineName;
        this.genericName = genericName;
        this.companyName = companyName;
        this.retailerName = retailerName;
        this.medicineCategory = medicineCategory;
        this.batchNumber = batchNumber;
        this.manufacturingDate = manufacturingDate;
        this.expiryDate = expiryDate;
        this.medicineQuantity = medicineQuantity;
        this.medicineMrp = medicineMrp;
        this.medicineCost = medicineCost;
        this.medicineDesc = medicineDesc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRetailerName() {
        return retailerName;
    }

    public void setRetailerName(String retailerName) {
        this.retailerName = retailerName;
    }

    public String getMedicineCategory() {
        return medicineCategory;
    }

    public void setMedicineCategory(String medicineCategory) {
        this.medicineCategory = medicineCategory;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public Date getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(Date manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Long getMedicineQuantity() {
        return medicineQuantity;
    }

    public void setMedicineQuantity(Long medicineQuantity) {
        this.medicineQuantity = medicineQuantity;
    }

    public Double getMedicineMrp() {
        return medicineMrp;
    }

    public void setMedicineMrp(Double medicineMrp) {
        this.medicineMrp = medicineMrp;
    }

    public Double getMedicineCost() {
        return medicineCost;
    }

    public void setMedicineCost(Double medicineCost) {
        this.medicineCost = medicineCost;
    }

    public String getMedicineDesc() {
        return medicineDesc;
    }

    public void setMedicineDesc(String medicineDesc) {
        this.medicineDesc = medicineDesc;
    }

    @Override
    public String toString() {
        return "ItemDetails{" +
                "id=" + id +
                ", medicineName='" + medicineName + '\'' +
                ", genericName='" + genericName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", retailerName='" + retailerName + '\'' +
                ", medicineCategory='" + medicineCategory + '\'' +
                ", batchNumber='" + batchNumber + '\'' +
                ", manufacturingDate=" + manufacturingDate +
                ", expiryDate=" + expiryDate +
                ", medicineQuantity=" + medicineQuantity +
                ", medicineMrp=" + medicineMrp +
                ", medicineCost=" + medicineCost +
                ", medicineDesc='" + medicineDesc + '\'' +
                '}';
    }
}
