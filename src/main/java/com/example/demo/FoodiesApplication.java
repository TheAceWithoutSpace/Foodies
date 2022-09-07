package com.example.demo;

import com.example.demo.Service.UserService.UserService;
import com.example.demo.model.AppUser;
import com.example.demo.model.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
public class FoodiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodiesApplication.class, args);
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return args ->{
			userService.saveRole(new Role(null,"User"));
			userService.saveRole(new Role(null,"courier"));
			userService.saveRole(new Role(null,"BusinessUser"));
			userService.saveRole(new Role(null,"Admin"));
			userService.saveUser(new AppUser(null,"Jin","Hgon","Jin",
					"1234","0543323005","",
					false, new Date() ,null,new ArrayList<>()));
			userService.saveUser(new AppUser(null,"Joe","gong","Joe",
					"1234","0543323045","",
					false, new Date() ,null,new ArrayList<>()));
			userService.saveUser(new AppUser(null,"Bil","jil","Bil",
					"1234","0543322005","",
					false, new Date() ,null,new ArrayList<>()));
			userService.addRoleToUser("Bil","User");
			userService.addRoleToUser("Joe","Admin");
//			userService.addRoleToUser("Lil","Admin");
		};
	}
}
