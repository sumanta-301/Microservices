package com.sumanta.items.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class SearchCriteria {

    private String id;
    private String brandName;
    private String genericName;
    private String medicineCategory;
    private Integer medicineCount;
    private Double medicineCost;
    private Double medicineMrp;
    private LocalDate mfgDate;
    private LocalDate expDate;
    private String batchNumber;
    private String retailerId;

}
