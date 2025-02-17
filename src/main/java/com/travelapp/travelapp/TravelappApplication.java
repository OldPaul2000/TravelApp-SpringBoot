package com.travelapp.travelapp;

import com.travelapp.travelapp.repository.JWTRepository;
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
	 * -Build file upload rest endpoints. Finish implementing proper functionality for TouristicPicture upload
	 *
	 * -If the jwt is expired and the user calls logout endpoint the token cannot be removed from database
	 *  because the it's expired and we can't get the claims from it (userId) in order to find
	 *  the token in the database by userId
     */

	@Bean
	public CommandLineRunner commandLineRunner(JWTRepository repository){
		return runner -> {

		};
	};

}

