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
	 * -Refactor REST endpoints names
	 * -Implement additional necessary methods
	 * -TEST AGAIN all REST endpoints
	 * */


	@Bean
	public CommandLineRunner commandLineRunner(UserRepository userRepository, UserService userService){
		return runner -> {


		};
	};

}

