package com.sumanta.userService.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name="customer_name")
public class User {
    @Id
    private  String userId;
    @NotBlank
    @NotEmpty
    private String name;
    private String email;
    @NotNull
    private long mobileNumber;
    @Column(name="GST_number")
    private String GSTNumber;
    private String address1;
    private String address2;
    @NotBlank
    private String distName;
    @NotBlank
    private int pincode;

    @Transient
    private List<Retailer> retailers= new ArrayList<>();

}
