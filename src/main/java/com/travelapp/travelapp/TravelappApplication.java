package com.travelapp.travelapp;

import com.travelapp.travelapp.model.userrelated.User;
import com.travelapp.travelapp.repository.PictureRepository;
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


	/**TODO
	 * -Implement all cascade types properly
	 * -Refactor all variables, REST endpoints and methods names
	 * -Implement additional necessary methods
	 * -TEST AGAIN all REST endpoints
	 * */



	@Bean
	public CommandLineRunner commandLineRunner(PictureRepository pictureRepository, UserRepository userRepository){
		return runner -> {

			User user = userRepository.findUserByIdWithInfoAndRoles(1);
			System.out.println("In commandLineRunner");
			System.out.println(user);
//			System.out.println(user.getTouristicPictures());


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
