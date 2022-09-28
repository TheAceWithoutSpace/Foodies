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
	@Bean
	CommandLineRunner run(UserService userService,
						  BusinessService businessService,
						  CourierService courierService,
						  CustomerService customerService,
						  ShipmentService shipmentService){
		return args ->{
//			AppUser appUser= new AppUser("bob",
//					"marli","Bil",
//					"Bil@gmail.com",
//					"password",
//					"0543323009",
//					null,USER);
//			AppUser appUser1= new AppUser("bob",
//					"marli","Bil1",
//					"Bil1@gmail.com",
//					"password",
//					"0543325609",
//					null,COURIER);
//			userService.saveUser(appUser);
//			userService.saveUser(appUser1);

//			shipments shipment= new shipments(
//					"loction",
//					"0543323489",
//					true, 0,
//					false,"Blop","","",""
////					customerService.getCustomer("bob").get().get(0),
////					businessService.getBusiness("Archi"),
////					courierService.getCourier(userService.getUser("Bil").get()).get()
//			);
//			shipmentService.saveShipment(shipment);
//			System.out.println(userService.getUser("Bil").get());
		};
	}
}
