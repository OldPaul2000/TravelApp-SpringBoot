package com.travelapp.travelapp;

import com.travelapp.travelapp.repository.PlaceRepository;
import com.travelapp.travelapp.service.PictureService;
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
	 *
	 * -Implement currentTokenIsValid method in logout service if jwt is expired
	 *  and the user requests logout endpoint the token cannot be removed from database
	 *  because the jwt is expired and the claims can't be extracted from it (userId)
	 *  in order to find the token in the database by userId
	 *
	 *  -Implement pictures queries by place type
	 *
	 *  - Check if "SELECT pp.touristicPicture FROM PicturePlace pp WHERE pp.city.city = :name"
	 *    could be changed to "SELECT TouristicPicture FROM TouristicPicture tp WHERE tp.picturePlace.city.city = :name"
	 *    and if is more efficient
	 */

	@Bean
	public CommandLineRunner commandLineRunner(PlaceRepository placeRepository, PictureService pictureService){
		return runner -> {

		};
	};

}

