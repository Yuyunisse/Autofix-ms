package com.example.reparaciones_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ReparacionesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReparacionesServiceApplication.class, args);
	}

}
