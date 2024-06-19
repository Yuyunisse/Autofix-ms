package com.example.historial_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class HistorialServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HistorialServiceApplication.class, args);
	}

}
