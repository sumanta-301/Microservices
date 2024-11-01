package com.sumanta.items.controller;

import com.sumanta.items.dto.SearchCriteria;
import com.sumanta.items.entities.Item;
import com.sumanta.items.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/items")

public class ItemController {
    @Autowired
    private ItemService itemService;
    @PostMapping

  public ResponseEntity<Item> createItems(@RequestBody Item item) {
        String id = UUID.randomUUID().toString();
        item.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.createItem(item));
  }

  @GetMapping("/{id}")
    public ResponseEntity<Item> getbyId(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.getHotel(id));
    }
    @GetMapping()
    public ResponseEntity<List<Item>> getAll (
            @RequestParam(value="pageSize",required = false, defaultValue = "5") Integer pageSize,
            @RequestParam(value="pageNumber",required = false, defaultValue = "0") Integer pageNumber
    ) {
        System.out.println("printing the pageSize & pageNumber"+ pageSize + "" + pageNumber);
        return ResponseEntity.ok(itemService.getAll(pageSize, pageNumber));
    }
//    @GetMapping("/retailerId/{retailerId}")
//
//    public ResponseEntity<Item> getByRetailerId(@PathVariable String  retailerId) {
//        return ResponseEntity.status(HttpStatus.OK).body((Item) itemService.findByRetailerId(retailerId));
//    }
    @GetMapping("/retailerId/{retailerId}")
    public ResponseEntity<List<Item>> getAllByRetailerId(@PathVariable String retailerId) {
        return ResponseEntity.status(HttpStatus.OK).body((List<Item>) itemService.findAllByRetailerId(retailerId));
    }

    @PostMapping("/search")
    public ResponseEntity<List<Item>> searchItems(@RequestBody SearchCriteria searchCriteria) {
        return ResponseEntity.status(HttpStatus.OK).body((List<Item>) itemService.searchItems(searchCriteria));
    }

    @GetMapping("/expiring-soon/{daysUntillExpiration}")

    public ResponseEntity<List<Item>> getExpiringItems(@PathVariable int daysUntillExpiration) {
        return ResponseEntity.status(HttpStatus.OK).body((List<Item>) itemService.findItemsExpiringSoon(daysUntillExpiration));
    }
}

