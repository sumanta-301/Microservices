package com.example.medicine.itemStoreService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name="retailer_details")

public class RetailerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String retailerName;
    @Column(name="retailer_id")
    private Long retailerId;
    private String retailerGstNumber;
    private String retailerAddress;
    private String retailerContactNo;
}
