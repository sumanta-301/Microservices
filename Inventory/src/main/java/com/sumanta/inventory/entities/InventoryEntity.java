package com.sumanta.inventory.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "inventory_items")

public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;
    private Integer quantity;
    private String brandName;
    private String genericName;
    private String medicineCategory;
   // private Integer medicineCount;
    private Double medicineCost;
    private Double medicineMrp;
    private LocalDate mfgDate;
    private LocalDate expDate;
    private String batchNumber;
    private String retailerId;
    private double totalCost;
}
