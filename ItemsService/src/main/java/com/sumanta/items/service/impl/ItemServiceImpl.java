package com.sumanta.items.service.impl;

import com.sumanta.items.dto.ItemMessage;
import com.sumanta.items.dto.SearchCriteria;
import com.sumanta.items.entities.Item;
import com.sumanta.items.exception.ResourceNotFoundException;
import com.sumanta.items.repository.ItemRepository;
import com.sumanta.items.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
@Slf4j

public class ItemServiceImpl implements ItemService {
    Logger logger  = LoggerFactory.getLogger(ItemServiceImpl.class);
    @Autowired
    private ItemRepository itemRepository;
    @Override
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item editItem(String id) {
        return null;
    }

    @Override
    public List<Item> getAll(Integer pageSize, Integer pageNumber) {

        Page<Item> itemPage = itemRepository.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.Direction.DESC));
        System.out.println(itemPage.getTotalPages());
        System.out.println(itemPage.getTotalElements());

        List<Item> itemList = itemPage.stream().toList();
        return itemList;


        // return itemRepository.findAll();
    }

    @Override
    public Item getHotel(String id) {
        logger.info("Items by item id {}",itemRepository.findById(id));
        return itemRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("no items found"));

    }

    @Override
    public Item findByRetailerId(String retailerId) {
        Item retailerId1 =itemRepository.findByRetailerId(retailerId);
        logger.info("item found by retailer Id{} " , retailerId1);
        if (retailerId1 != null) {
            return retailerId1;
        } else {
            throw new ResourceNotFoundException("no items found for given retailerId");
        }
    }

    @Override
    public List<Item> findAllByRetailerId(String retailerId) {
        List<Item> items = itemRepository.findAllByRetailerId(retailerId);
        logger.info("findAllItems by retailer Id {} ", items);
        if(!items.isEmpty()) {
            return items;
        } else {
            throw new ResourceNotFoundException("No items found for the given retailer Id");
        }
    }

    @Override
    public List<Item> searchItems(SearchCriteria criteria) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        // Create an instance of the Item class as an example
        Item itemExample = new Item();
        itemExample.setMedicineCategory(criteria.getMedicineCategory());
        itemExample.setBrandName(criteria.getBrandName());
        itemExample.setGenericName(criteria.getGenericName());
        itemExample.setRetailerId(criteria.getRetailerId());
        itemExample.setBatchNumber(criteria.getBatchNumber());
        itemExample.setId(criteria.getId());
        itemExample.setExpDate(criteria.getExpDate());
        itemExample.setMedicineCost(criteria.getMedicineCost());
        itemExample.setMedicineMrp(criteria.getMedicineMrp());
        itemExample.setMedicineCount(criteria.getMedicineCount());


        // Create an Example instance using Example.of() with the matcher
        Example<Item> example = Example.of(itemExample, matcher);
        System.out.println(example.toString());


        // Use the example to query the database
        return itemRepository.findAll(example);
    }

    @Override
    public List<Item> findItemsExpiringSoon(int daysUntilExpiration) {
        LocalDate now = LocalDate.now();
        LocalDate expirationThreshHold = now.plusDays(daysUntilExpiration);
        return itemRepository.findAllByExpDateBetween(now, expirationThreshHold);
    }

    @Override
    public void updateItem(ItemMessage itemMessage) {
        ModelMapper modelMapper = new ModelMapper();
        Item mappedItems = modelMapper.map(itemMessage, Item.class);
        itemRepository.save(mappedItems);

    }

}
