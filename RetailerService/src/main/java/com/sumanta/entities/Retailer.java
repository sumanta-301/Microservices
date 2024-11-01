package com.sumanta.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
// we can use Record to save the data in our database, this will eliminate the
//needs of getter & setters but remember one thing that Records are immutable

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="retailer_details")
public class Retailer {
    @Id
    private String retailerId;
    private String retailerName;
    private String reatailerGstNumber;
    private String userId;
}
