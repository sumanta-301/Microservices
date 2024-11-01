package com.example.medicine.itemStoreService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ItemStoreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemStoreServiceApplication.class, args);
	}

}
