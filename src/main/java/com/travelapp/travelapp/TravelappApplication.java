package com.travelapp.travelapp;

import com.travelapp.travelapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class TravelappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelappApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(UserRepository userRepository){
		return runner -> {


		};
	};


	private void testForPictures(){
		//databaseTestTouristicPic.postNewPicture();
	}
	private void testForLocations(){
//			databaseTest.addNewCountry("Romania");
//			databaseTest.addCityToCountry("Romania", "Alba Iulia");
//			databaseTest.addCommuneToCity("Alba Iulia", "Gârda de Sus");
//			databaseTest.addVillageToCommune("Gârda de Sus", "Dealu Frumos");
//			databaseTest.addNewPlaceName("Cascada Pisoaia");
	}

	private void testForUserRelated(){
		//databaseTest.findUserByFullName("Karo", "Molnar");
		//databaseTest.registerNewUser();
		//databaseTest.addNewRoleToUser();
		//databaseTest.removeRoleFromUser();
		//databaseTest.deleteUser();
		//databaseTest.getUser();
		//databaseTest.updateUserProfilePic();
		//databaseTest.deleteUser();
	}



}
