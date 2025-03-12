package com.travelapp.travelapp.repository;

import com.travelapp.travelapp.model.locations.*;

import java.util.List;

public interface PlaceRepository {

    Country findCountryWithCities(String name);

    City findCityWithCommunes(String name);

    Commune findCommuneWithVillages(String name);

    Village findVillage(String name);

    void persistNewCountry(Country country);

    Country findCountryByIdWithCities(int id);

    void mergeCountry(Country country);

    City findCityByIdWithCommunes(int id);

    void mergeCity(City city);

    Commune findCommuneByIdWithVillages(int id);

    void mergeCommune(Commune commune);

    void persistPlaceType(PlaceType placeType);

    List<PlaceType> findAllPlaceTypes();

    PlaceName findPlaceNameByName(String name);

    void persistNewPlaceName(PlaceName placeName);


}
