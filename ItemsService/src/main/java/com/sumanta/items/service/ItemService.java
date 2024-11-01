package com.sumanta.items.service;

import com.sumanta.items.dto.ItemMessage;
import com.sumanta.items.dto.SearchCriteria;
import com.sumanta.items.entities.Item;

import java.util.List;

public interface ItemService {
   Item  createItem(Item item);
   Item editItem(String id);

   List<Item> getAll(Integer offSet, Integer pageSize);

   Item getHotel(String id);
  Item findByRetailerId(String retailerId);
  List<Item> findAllByRetailerId(String retailerId);
  List<Item> searchItems(SearchCriteria criteria);
  List<Item> findItemsExpiringSoon(int daysUntilExpiration);


    void updateItem(ItemMessage itemMessage);
}
