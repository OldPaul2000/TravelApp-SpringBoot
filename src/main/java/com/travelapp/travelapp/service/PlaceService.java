package com.travelapp.travelapp.service;

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

import static com.travelapp.travelapp.restcontroller.exceptionhandling.customerrormessage.PlaceErrorMessages.*;

@Service
public class PlaceService {

    private PlaceRepository placeRepository;
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public Country getCountryWithCities(String countryName){
        try{
            return placeRepository.getCountryWithCities(countryName);
        }
        catch (EmptyResultDataAccessException e){
            throw new CountryNotFoundException(COUNTRY_NOT_FOUND.message());
        }
    }

    public City getCityWithCommunes(String cityName){
        try{
            return placeRepository.getCityWithCommunes(cityName);
        }
        catch (EmptyResultDataAccessException e){
            throw new CountryNotFoundException(CITY_NOT_FOUND.message());
        }
    }

    public Commune getCommuneWithVillages(String communeName){
        try{
            return placeRepository.getCommuneWithVillages(communeName);
        }
        catch (EmptyResultDataAccessException e){
            throw new CountryNotFoundException(COMMUNE_NOT_FOUND.message());
        }
    }

    public Village getVillage(String villageName){
        try{
            return placeRepository.getVillage(villageName);
        }
        catch (EmptyResultDataAccessException e){
            throw new VillageNotFoundException(VILLAGE_NOT_FOUND.message());
        }
    }

    public void addNewCountry(String countryName){
        try{
            Country country = new Country(countryName);
            placeRepository.addNewCountry(country);
        }
        catch (DataIntegrityViolationException e){
            throw new CountryAlreadyExistsException(ALREADY_EXISTING_COUNTRY.message());
        }
    }

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
