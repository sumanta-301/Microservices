package com.sumanta.items.repository;

import com.sumanta.items.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, String> {
    Item findByRetailerId(String retailerId);
    List<Item> findAllByRetailerId(String retailerId);
    List<Item> findAllByExpDate(Date expiryDate);
    List<Item> findAllByBrandName(String brandName);
    List<Item> findAllByGenericName(String genericName);
    List<Item> findAllByBatchNumber(String batchNumber);
    List<Item> findAllByMedicineCategory(String medicationCategory);
    List<Item> findAllByExpDateBetween(LocalDate startDate, LocalDate endDate);

}
