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

    public CountryDTOGet getCountryWithCities(String countryName){
        try{
            Country country = placeRepository.findCountryWithCities(countryName);
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

    public CityDTOGet getCityWithCommunes(String cityName){
        try{
            City city = placeRepository.findCityWithCommunes(cityName);
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

    public Commune getCommuneWithVillages(String communeName){
        try{
            return placeRepository.findCommuneWithVillages(communeName);
        }
        catch (EmptyResultDataAccessException e){
            throw new CountryNotFoundException(COMMUNE_NOT_FOUND.message());
        }
    }

    public Village getVillage(String villageName){
        try{
            return placeRepository.findVillage(villageName);
        }
        catch (EmptyResultDataAccessException e){
            throw new VillageNotFoundException(VILLAGE_NOT_FOUND.message());
        }
    }

    public void addNewCountry(String countryName){
        try{
            Country country = new Country(countryName);
            placeRepository.persistNewCountry(country);
        }
        catch (DataIntegrityViolationException e){
            throw new CountryAlreadyExistsException(ALREADY_EXISTING_COUNTRY.message());
        }
    }

    public void addNewCityForCountry(int countryId, String cityName){
        Country country = placeRepository.findCountryByIdWithCities(countryId);
        try{
            City city = new City(cityName);
            country.addCity(city);
            city.setCountry(country);
            placeRepository.mergeCountry(country);
        }
        catch (DataIntegrityViolationException e){
            throw new CityAlreadyExistsException(ALREADY_EXISTING_CITY.message());
        }
    }

    public void addNewCommuneForCity(int cityId, String communeName){
        City city = placeRepository.findCityByIdWithCommunes(cityId);
        try{
            Commune commune = new Commune(communeName);
            city.addCommune(commune);
            commune.setCity(city);
            placeRepository.mergeCity(city);
        }
        catch (DataIntegrityViolationException e){
            throw new CommuneAlreadyExistsException(ALREADY_EXISTING_COMMUNE.message());
        }
    }

    public void addNewVillageForCommune(int communeId, String villageName){
        Commune commune = placeRepository.findCommuneByIdWithVillages(communeId);
        try{
            Village village = new Village(villageName);
            commune.addVillage(village);
            village.setCommune(commune);
            placeRepository.mergeCommune(commune);
        }
        catch (DataIntegrityViolationException e){
            throw new CommuneAlreadyExistsException(ALREADY_EXISTING_VILLAGE.message());
        }
    }

}
