package com.travelapp.travelapp;

import com.travelapp.travelapp.repository.PlaceRepository;
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
	 * -Implement profile picture removal when uploading a new profile picture
	 *
	 * -Implement file format checker
	 *
	 * -Login method must return all info related to the user
	 *
	 * -Implement currentTokenIsValid method in logout service if jwt is expired
	 *  and the user requests logout endpoint the token cannot be removed from database
	 *  because the jwt is expired and the claims can't be extracted from it (userId)
	 *  in order to find the token in the database by userId
	 *
	 *  -Implement place_type. Classes to modify:
	 *  		PlaceController,
	 *  	    PictureRepositoryImpl,
	 *  	    PlaceRepositoryImpl,
	 *  	    PictureService,
	 *  	    PlaceService,
	 */

	@Bean
	public CommandLineRunner commandLineRunner(PlaceRepository placeRepository){
		return runner -> {
//			System.out.println(placeRepository.findAllPlaceTypes());
		};
	};

}

