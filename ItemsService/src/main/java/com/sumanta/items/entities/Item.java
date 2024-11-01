package com.sumanta.items.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="items")
public class Item {
    @Id
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
    private String userId;

}
