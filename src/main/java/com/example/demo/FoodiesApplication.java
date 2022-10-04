package com.example.demo;

import com.example.demo.api.Business.BusinessService;
import com.example.demo.api.Courier.CourierService;
import com.example.demo.api.Customer.CustomerService;
import com.example.demo.api.shipments.ShipmentService;
import com.example.demo.api.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FoodiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodiesApplication.class, args);
	}
}
