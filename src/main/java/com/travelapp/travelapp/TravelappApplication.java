package com.travelapp.travelapp;

import com.travelapp.travelapp.repository.UserRepository;
import com.travelapp.travelapp.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class TravelappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelappApplication.class, args);
	}

    /**TODO
     * Implement security and JWT authentication
     */

	@Bean
	public CommandLineRunner commandLineRunner(UserRepository userRepository, UserService userService){
		return runner -> {


		};
	};

}

