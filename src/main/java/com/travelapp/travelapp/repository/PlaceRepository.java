package com.travelapp.travelapp.repository;

import com.travelapp.travelapp.model.locations.*;

public interface PlaceRepository {

    Country getCountryWithCities(String name);

    Country getCountryByIdWithCities(int id);

    City getCityWithCommunes(String name);

    Village getVillage(String name);

    City getCityByIdWithCommunes(int id);

    Commune getCommuneWithVillages(String name);

    Commune getCommuneByIdWithVillages(int id);

    PlaceName getPlaceNameByName(String name);

    void addNewCountry(Country country);

    void addPlaceName(PlaceName placeName);

    void updateCountry(Country country);

    void updateCity(City city);

    void updateCommune(Commune commune);



//    void addNewCity(City city);
//
//    void addNewCommune(Commune commune);
//
//    void addNewVillage(Village village);

}
