package com.travelapp.travelapp;

import com.travelapp.travelapp.model.userrelated.User;
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

			User user = userRepository.findUserByIdWithInfoAndRoles(1);
			System.out.println("In commandLineRunner");
			System.out.println(user);

//			userService.deleteUserAccount(1, false);
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
