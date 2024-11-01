package com.sumanta.saleService.controller;
import com.sumanta.saleService.entities.SalesEntity;
import com.sumanta.saleService.service.SaleService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@Validated
@RequestMapping("/sales")

public class SaleController {
    @Autowired
    private SaleService saleService;
    @PostMapping()
    public ResponseEntity<SalesEntity> createSaleRecord ( @Valid @RequestBody SalesEntity sales) {

           double total= sales.getMrp() * sales.getQuantitySold();
           sales.setTotalAmount(total);
           sales.setTimestamp(LocalDate.now());
            return ResponseEntity.status(HttpStatus.CREATED).body(saleService.createSalesRecords(sales));
    }

}
