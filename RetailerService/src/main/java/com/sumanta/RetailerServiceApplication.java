package com.sumanta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class RetailerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailerServiceApplication.class, args);
	}

}
