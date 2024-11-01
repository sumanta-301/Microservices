package com.sumanta.userService.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Item {
    @JsonProperty("id")
    private String id;
    @JsonProperty("brandName")
    private String brandName;
    @JsonProperty("genericName")
    private String genericName;
    @JsonProperty("medicineCategory")
    private String medicineCategory;
    @JsonProperty("medicineCount")
    private int medicineCount;
    @JsonProperty("medicineCost")
    private Double medicineCost;
    @JsonProperty("medicineMrp")
    private Double medicineMrp;
    @JsonProperty("mfgDate")
    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDate mfgDate;
    @JsonProperty("expDate")
    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDate expDate;
    @JsonProperty("batchNumber")
    private String batchNumber;
    @JsonProperty("retailerId")
    private String retailerId;
    @JsonProperty("userId")
    private String userId;
}
