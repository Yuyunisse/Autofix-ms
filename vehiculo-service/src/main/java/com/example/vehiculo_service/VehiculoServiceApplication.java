package com.example.vehiculo_service;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class VehiculoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehiculoServiceApplication.class, args);
	}

}
