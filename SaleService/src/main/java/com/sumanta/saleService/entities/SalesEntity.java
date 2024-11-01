package com.sumanta.saleService.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="sales_records")
public class SalesEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    private Long id;
    @NotBlank
    private String brandName;
    @NotBlank
    private String batchNumber;
    @NotBlank
    private Integer quantitySold;
    private Double totalAmount;
    private Double mrp;
    private LocalDate timestamp;

}
