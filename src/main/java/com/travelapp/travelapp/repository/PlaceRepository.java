package com.travelapp.travelapp.repository;

import com.travelapp.travelapp.model.locations.*;

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



    PlaceName findPlaceNameByName(String name);

    void persistNewPlaceName(PlaceName placeName);


}
