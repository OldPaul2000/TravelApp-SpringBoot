package com.travelapp.travelapp.service;

import com.travelapp.travelapp.dto.places.CityDTOGet;
import com.travelapp.travelapp.dto.places.CommuneDTOGet;
import com.travelapp.travelapp.dto.places.CountryDTOGet;
import com.travelapp.travelapp.model.locations.City;
import com.travelapp.travelapp.model.locations.Commune;
import com.travelapp.travelapp.model.locations.Country;
import com.travelapp.travelapp.model.locations.Village;
import com.travelapp.travelapp.repository.PlaceRepository;
import com.travelapp.travelapp.restcontroller.exceptionhandling.places.city.CityAlreadyExistsException;
import com.travelapp.travelapp.restcontroller.exceptionhandling.places.commune.CommuneAlreadyExistsException;
import com.travelapp.travelapp.restcontroller.exceptionhandling.places.country.CountryAlreadyExistsException;
import com.travelapp.travelapp.restcontroller.exceptionhandling.places.country.CountryNotFoundException;
import com.travelapp.travelapp.restcontroller.exceptionhandling.places.village.VillageNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.travelapp.travelapp.restcontroller.exceptionhandling.customerrormessage.PlaceErrorMessages.*;

@Service
public class PlaceService {

    private PlaceRepository placeRepository;
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    /* Works */
    public CountryDTOGet getCountryWithCities(String countryName){
        try{
            Country country = placeRepository.getCountryWithCities(countryName);
            List<CityDTOGet> cities = country.getCities()
                    .stream()
                    .map(city -> {
                        return new CityDTOGet(
                                city.getId(),
                                city.getCity(),
                                null
                        );
                    }).toList();

            return new CountryDTOGet(
                    country.getId(),
                    country.getCountry(),
                    cities
            );
        }
        catch (EmptyResultDataAccessException e){
            throw new CountryNotFoundException(COUNTRY_NOT_FOUND.message());
        }
    }

    /* Works */
    public CityDTOGet getCityWithCommunes(String cityName){
        try{
            City city = placeRepository.getCityWithCommunes(cityName);
            List<CommuneDTOGet> communes = city.getCommunes()
                    .stream()
                    .map(commune -> {
                        return new CommuneDTOGet(
                                commune.getId(),
                                commune.getCommune(),
                                null
                        );
                    }).toList();

            return new CityDTOGet(
                    city.getId(),
                    city.getCity(),
                    communes
            );
        }
        catch (EmptyResultDataAccessException e){
            throw new CountryNotFoundException(CITY_NOT_FOUND.message());
        }
    }

    /* Works */
    public Commune getCommuneWithVillages(String communeName){
        try{
            return placeRepository.getCommuneWithVillages(communeName);
        }
        catch (EmptyResultDataAccessException e){
            throw new CountryNotFoundException(COMMUNE_NOT_FOUND.message());
        }
    }

    /* Works */
    public Village getVillage(String villageName){
        try{
            return placeRepository.getVillage(villageName);
        }
        catch (EmptyResultDataAccessException e){
            throw new VillageNotFoundException(VILLAGE_NOT_FOUND.message());
        }
    }

    /* Works */
    public void addNewCountry(String countryName){
        try{
            Country country = new Country(countryName);
            placeRepository.addNewCountry(country);
        }
        catch (DataIntegrityViolationException e){
            throw new CountryAlreadyExistsException(ALREADY_EXISTING_COUNTRY.message());
        }
    }

    /* Works */
    public void addNewCityForCountry(int countryId, String cityName){
        Country country = placeRepository.getCountryByIdWithCities(countryId);
        try{
            City city = new City(cityName);
            country.addCity(city);
            city.setCountry(country);
            placeRepository.updateCountry(country);
        }
        catch (DataIntegrityViolationException e){
            throw new CityAlreadyExistsException(ALREADY_EXISTING_CITY.message());
        }
    }

    /* Works */
    public void addNewCommuneForCity(int cityId, String communeName){
        City city = placeRepository.getCityByIdWithCommunes(cityId);
        try{
            Commune commune = new Commune(communeName);
            city.addCommune(commune);
            commune.setCity(city);
            placeRepository.updateCity(city);
        }
        catch (DataIntegrityViolationException e){
            throw new CommuneAlreadyExistsException(ALREADY_EXISTING_COMMUNE.message());
        }
    }

    /* Works */
    public void addNewVillageForCommune(int communeId, String villageName){
        Commune commune = placeRepository.getCommuneByIdWithVillages(communeId);
        try{
            Village village = new Village(villageName);
            commune.addVillage(village);
            village.setCommune(commune);
            placeRepository.updateCommune(commune);
        }
        catch (DataIntegrityViolationException e){
            throw new CommuneAlreadyExistsException(ALREADY_EXISTING_VILLAGE.message());
        }
    }

}
