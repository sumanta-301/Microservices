package com.sumanta.repositories;

import com.sumanta.entities.Retailer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RetailerRepository extends JpaRepository<Retailer, String> {
    List<Retailer> findByUserId(String userId);
    Retailer findByRetailerId(String retailerId);

}
