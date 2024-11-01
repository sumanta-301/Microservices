package com.sumanta.userService.externalServices;

import com.sumanta.userService.entities.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ITEMSERVICE")

public interface ItemService {
        @GetMapping("/items/retailerId/{retailerId}")
        List<Item> getItem (@PathVariable String retailerId);

}
