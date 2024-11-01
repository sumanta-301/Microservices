package com.sumanta.userService.entities;

import jakarta.persistence.Id;
import lombok.*;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Retailer {
    @Id
    private String retailerId;
    private String retailerName;
    private String reatailerGstNumber;
    private String userId;
    private Item item;
}
