package com.example.medicine.itemStoreService.service.impl;

import com.example.medicine.itemStoreService.entity.ItemDetails;
import com.example.medicine.itemStoreService.repository.ItemRepository;
import com.example.medicine.itemStoreService.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service


public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        super();
        this.itemRepository = itemRepository;
    }


    @Override
    public List<ItemDetails> saveItems(List<ItemDetails> itemDetailsList) {

//        for(ItemDetails itemDetails : itemDetailsList) {
//            ItemDetails existingDetails = itemRepository.findByMedicineNameAndBatchNumber(itemDetails.getMedicineName(),itemDetails.getBatchNumber());
//            if(existingDetails !=null) {
//           Long quaintity= itemDetails.getMedicineQuantity();
//         Double mrp = itemDetails.getMedicineMrp();
//          Double cost = quaintity * mrp;
//        itemDetails.setMedicineCost(cost);
//
//                // scenario 1: item exist update quantity and cost
//                Long newQuantity = existingDetails.getMedicineQuantity() + itemDetails.getMedicineQuantity();
//                Double newCost = newQuantity * itemDetails.getMedicineMrp();
//                itemDetails.setMedicineQuantity(newQuantity);
//                itemDetails.setMedicineCost(newCost);
//        }
        //return itemRepository.saveAll(itemDetailsList);
        List<ItemDetails> savedItems = new ArrayList<>();

        for (ItemDetails item : itemDetailsList) {
            // Find existing item by medicine name and batch number
//            ItemDetails existingItem = itemRepository.findByMedicineNameAndBatchNumber(
//                    item.getMedicineName(), item.getBatchNumber());

            //find existing item by medicine name and retailer name
//            ItemDetails existingItem = itemRepository.findByMedicineNameAndRetailerName(
//                    item.getMedicineName(), item.getRetailerName()
//            );

            //find existing records by medicine name reatailer name and batch number
            ItemDetails existingItem = itemRepository.findByMedicineNameAndRetailerNameAndBatchNumber(
                   item.getMedicineName(), item.getRetailerName(), item.getBatchNumber()
            );

            if (existingItem != null) {
                // Handle the update logic for existing items
                // Calculate new quantity and total cost
                Long newQuantity = existingItem.getMedicineQuantity() + item.getMedicineQuantity();
                Double newMrp = item.getMedicineMrp();
                Double newCost = newQuantity * item.getMedicineMrp();


                existingItem.setMedicineQuantity(newQuantity);
                existingItem.setMedicineCost(newCost);
                existingItem.setMedicineMrp(newMrp);

                System.out.println("existing record found updating them "+ existingItem.getMedicineName());


                // Add the updated item to the list of saved items
                savedItems.add(itemRepository.save(existingItem));
                System.out.println("updated quantity " +existingItem.getMedicineQuantity() );
                System.out.println("updated cost " + existingItem.getMedicineCost());
            } else {
                // Calculate the cost based on quantity and MRP for the new item
                Long quantity = item.getMedicineQuantity();
                Double mrp = item.getMedicineMrp();
                Double cost = quantity * mrp;
                item.setMedicineCost(cost);

                System.out.println("new record created "+ item.getMedicineName() );

                // Add the new item to the list of saved items
                savedItems.add(itemRepository.save(item));

            }

        }
        return savedItems;
    }

    @Override
    public ItemDetails updateItems(ItemDetails itemDetails, long id) {
        return null;
    }

    @Override
    public ItemDetails updateItemsStock(ItemDetails itemDetails, long id) {
        return null;
    }

    @Override
    public void deleteItems(long id) {

    }

    @Override
    public List<ItemDetails> getAllItems() {
        return null;
    }

    @Override
    public List<ItemDetails> getItemsByCategory(String medicineCategory) {
        return null;
    }

    @Override
    public List<ItemDetails> getItemsByExpiryDate(Date expiryDate) {
        return null;
    }

    @Override
    public List<ItemDetails> getItemsByMedicineName(String medicineName) {
        return null;
    }

    @Override
    public List<ItemDetails> getItemsByGenericname(String genericName) {
        return null;
    }
}
