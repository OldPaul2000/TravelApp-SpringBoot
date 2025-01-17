package com.travelapp.travelapp.restcontroller;

import com.travelapp.travelapp.dto.places.CityDTOGet;
import com.travelapp.travelapp.dto.places.CountryDTOGet;
import com.travelapp.travelapp.model.locations.Commune;
import com.travelapp.travelapp.model.locations.Village;
import com.travelapp.travelapp.service.PlaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/places")
public class PlaceController {

    private PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<CountryDTOGet> getCountryAndCities(@PathVariable("country") String countryName) {
        CountryDTOGet country = placeService.getCountryWithCities(countryName);
        return ResponseEntity.ok(country);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<CityDTOGet> getCityAndCommunes(@PathVariable("city") String cityName){
        CityDTOGet city = placeService.getCityWithCommunes(cityName);
        return ResponseEntity.ok(city);
    }

    @GetMapping("/commune/{commune}")
    public ResponseEntity<Commune> getCommuneAndVillages(@PathVariable("commune") String communeName){
        Commune commune = placeService.getCommuneWithVillages(communeName);
        return ResponseEntity.ok(commune);
    }

    @GetMapping("/village/{village}")
    public ResponseEntity<Village> getVillage(@PathVariable("village") String villageName){
        Village village = placeService.getVillage(villageName);
        return ResponseEntity.ok(village);
    }
    @PostMapping("/newCountry/{country}")
    public ResponseEntity<String> addCountry(@PathVariable String country){
        placeService.addNewCountry(country);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/newCity/{countryId}/{city}")
    public ResponseEntity<String> addCityToCountry(@PathVariable int countryId, @PathVariable String city){
        placeService.addNewCityForCountry(countryId, city);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/newCommune/{cityId}/{commune}")
    public ResponseEntity<String> addCommuneToCity(@PathVariable int cityId, @PathVariable String commune){
        placeService.addNewCommuneForCity(cityId, commune);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/newVillage/{communeId}/{village}")
    public ResponseEntity<String> addVillageToCommune(@PathVariable int communeId, @PathVariable String village){
        placeService.addNewVillageForCommune(communeId, village);
        return ResponseEntity.ok(null);
    }



}
