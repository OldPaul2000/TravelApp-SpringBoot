package com.travelapp.travelapp.repository.testing;

import com.travelapp.travelapp.model.MyEntity;
import com.travelapp.travelapp.model.locations.*;
import com.travelapp.travelapp.model.postedpictures.TouristicPicture;
import com.travelapp.travelapp.model.userrelated.Role;
import com.travelapp.travelapp.model.userrelated.User;

import java.util.List;

public interface AppDAO {

    //=======================================================================================================
    // FOR POSTEDPICTURES ENTITIES

    List<TouristicPicture> getTouristicPicturesByUser(User user);
    List<TouristicPicture> getTouristicPicturesByUserId(int id);

    //=======================================================================================================
    // FOR LOCATIONS ENTITIES

    Country getCountryByName(String name);

    Country getCountryByNameWithPicturePlaces(String name);

    City getCityByName(String name);

    City getCityByNameWithPicturePlaces(String name);

    Commune getCommuneByName(String name);

    Commune getCommuneByNameWithPicturePlaces(String name);

    Village getVillageByName(String name);

    Village getVillageByNameWithPicturePlaces(String name);

    PlaceName getPlaceNameByName(String name);

    PlaceName getPlaceNameByNameWithPicturePlaces(String name);

    //=======================================================================================================
    // FOR USER RELATED ENTITIES

    User getUserByIdWithPictureComments(int userId);

    User findUserById(int id);

    User findUserAndRolesById(int id);

    Role findRoleById(int id);

    User findUserAndUserInfoById(int id);

    User findUserByFullName(String firstName, String lastName);

    User findUserWithPicturesByFullName(String firstName, String lastName);

    //=======================================================================================================
    // FOR EVERY ENTITY

    MyEntity findById(Class<? extends MyEntity> resultClass, int id);

    void addNewEntry(MyEntity myEntity);

    void updateEntry(MyEntity myEntity);

    void deleteEntry(MyEntity myEntity);

}
