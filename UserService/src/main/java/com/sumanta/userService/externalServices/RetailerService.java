package com.sumanta.userService.externalServices;

import com.sumanta.userService.entities.Retailer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "RETAILERSERVICE")
public interface RetailerService {
    @GetMapping("/retailers/users/{userId}")
    Retailer[] getRetailer(@PathVariable("userId") String userId);
    @PostMapping("/retailers/")
    Retailer createRetailer(Retailer values);

}
