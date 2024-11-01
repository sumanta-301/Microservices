package com.sumanta.userService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
@Data
public class ItemMessage {
    private String id;
    private String brandName;
    private String genericName;
    private String medicineCategory;
    private Integer medicineCount;
    private Double medicineCost;
    private Double medicineMrp;
    //@JsonFormat(pattern = "YYYY-MM-DD")
    private LocalDate mfgDate;
   // @JsonFormat(pattern = "YYYY-MM-DD")
    private LocalDate expDate;
    private String batchNumber;
    private String retailerId;
    private String userId;

}
