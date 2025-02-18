package com.travelapp.travelapp;

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
     */

	/**TODO in the future
	 *
	 * -Implement file format checker
	 * -Set equalsIgnoreCase in CurrentUserVerifier.isCurrentUser(String name) method
	 *  (if the username is John in the database and you log in as john you will get errors
	 *  when trying to access some restpoints which verify if the logged in user's id is the
	 *  same as the id passes to restpoints)
	 *
	 * -If the jwt is expired and the user calls logout endpoint the token cannot be removed from database
	 *  because the jwt is expired and we can't get the claims from it (userId) in order to find
	 * 	the token in the database by userId
	 */

	@Bean
	public CommandLineRunner commandLineRunner(){
		return runner -> {



		};
	};

}

