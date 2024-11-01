package com.sumanta.saleService.repo;

import com.sumanta.saleService.entities.SalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<SalesEntity, Long> {
}
