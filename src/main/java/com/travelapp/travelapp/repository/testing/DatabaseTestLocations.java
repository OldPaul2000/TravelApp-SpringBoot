package com.travelapp.travelapp.repository.testing;

import com.travelapp.travelapp.model.locations.City;
import com.travelapp.travelapp.model.locations.Commune;
import com.travelapp.travelapp.model.locations.Country;
import com.travelapp.travelapp.model.locations.Village;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseTestLocations {

    private AppDAO appDAO;

    @Autowired
    public DatabaseTestLocations(AppDAO appDAO) {
        this.appDAO = appDAO;
    }


//    public void addNewCountry(String countryName){
//        Country country = new Country(countryName);
//        appDAO.addNewEntry(country);
//    }
//
//    @Transactional
//    public void addCityToCountry(String countryName, String cityName){
//        Country country = appDAO.getCountryByName(countryName);
//        City city = new City(cityName);
//        country.addCity(city);
//        city.setCountry(country);
//
//        appDAO.updateEntry(country);
//    }
//
//    @Transactional
//    public void addCommuneToCity(String cityName, String communeName){
//        City city = appDAO.getCityByName(cityName);
//        Commune commune = new Commune(communeName);
//        city.addCommune(commune);
//        commune.setCity(city);
//
//        appDAO.updateEntry(city);
//    }
//
//    @Transactional
//    public void addVillageToCommune(String communeName, String villageName){
//        Commune commune = appDAO.getCommuneByName(communeName);
//        Village village = new Village(villageName);
//        commune.addVillage(village);
//        village.setCommune(commune);
//
//        appDAO.updateEntry(commune);
//    }
//
//    @Transactional
//    public void addNewPlaceName(String name){
//        PlaceName placeName = new PlaceName(name);
//
//        appDAO.addNewEntry(placeName);
//    }
//
//    @Transactional
//    public void renameCity(String cityName, String newName){
//        City city = appDAO.getCityByName(cityName);
//        city.setCity(newName);
//        appDAO.updateEntry(city);
//    }

    public Country getCountryByName(String name){
        return appDAO.getCountryByName(name);
    }

    public City getCityByName(String name){
        return appDAO.getCityByName(name);
    }

    public Commune getCommuneByName(String name){
        return appDAO.getCommuneByName(name);
    }

    public Village getVillageByName(String name){
        return appDAO.getVillageByName(name);
    }

}
