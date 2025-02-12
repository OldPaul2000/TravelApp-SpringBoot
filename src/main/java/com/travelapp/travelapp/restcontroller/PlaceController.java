package com.travelapp.travelapp.restcontroller;

import com.travelapp.travelapp.dto.places.*;
import com.travelapp.travelapp.model.locations.Commune;
import com.travelapp.travelapp.model.locations.Village;
import com.travelapp.travelapp.service.PlaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/places")
public class PlaceController {

    private PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    /* Works */
    @GetMapping("/countries")
    public ResponseEntity<CountryDTOGet> getCountryAndCities(@RequestParam String country) {
        CountryDTOGet countryDTO = placeService.getCountryWithCities(country);
        return ResponseEntity.ok(countryDTO);
    }

    /* Works */
    @GetMapping("/cities")
    public ResponseEntity<CityDTOGet> getCityAndCommunes(@RequestParam String city){
        CityDTOGet cityDTO = placeService.getCityWithCommunes(city);
        return ResponseEntity.ok(cityDTO);
    }

    /* Works */
    @GetMapping("/communes")
    public ResponseEntity<Commune> getCommuneAndVillages(@RequestParam String commune){
        Commune communeDTO = placeService.getCommuneWithVillages(commune);
        return ResponseEntity.ok(communeDTO);
    }

    /* Works */
    @GetMapping("/villages")
    public ResponseEntity<Village> getVillage(@RequestParam String village){
        Village villageDTO = placeService.getVillage(village);
        return ResponseEntity.ok(villageDTO);
    }

    /* Works */
    @PostMapping("/countries")
    public ResponseEntity<String> addCountry(@RequestBody CountryDTOPost country){
        placeService.addNewCountry(country.country());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /* Works */
    @PostMapping("/cities/{countryId}")
    public ResponseEntity<String> addCityForCountry(@PathVariable int countryId, @RequestBody CityDTOPost city){
        placeService.addNewCityForCountry(countryId, city.city());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /* Works */
    @PostMapping("/communes/{cityId}")
    public ResponseEntity<String> addCommuneForCity(@PathVariable int cityId, @RequestBody CommuneDTOPost commune){
        placeService.addNewCommuneForCity(cityId, commune.commune());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /* Works */
    @PostMapping("/villages/{communeId}")
    public ResponseEntity<String> addVillageForCommune(@PathVariable int communeId, @RequestBody VillageDTOPost village){
        placeService.addNewVillageForCommune(communeId, village.village());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



}
