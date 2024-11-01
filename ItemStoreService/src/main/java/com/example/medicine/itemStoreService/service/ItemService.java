package com.example.medicine.itemStoreService.service;


import com.example.medicine.itemStoreService.entity.ItemDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


public interface ItemService {
    List<ItemDetails> saveItems(List<ItemDetails> itemDetails);
    ItemDetails updateItems(ItemDetails itemDetails, long id);

    ItemDetails updateItemsStock(ItemDetails itemDetails,long id);
    void deleteItems(long id);
    List<ItemDetails> getAllItems();
    List<ItemDetails> getItemsByCategory(String medicineCategory);
    List<ItemDetails> getItemsByExpiryDate(Date expiryDate);
    List<ItemDetails> getItemsByMedicineName(String medicineName);
    List<ItemDetails> getItemsByGenericname(String genericName);

}
