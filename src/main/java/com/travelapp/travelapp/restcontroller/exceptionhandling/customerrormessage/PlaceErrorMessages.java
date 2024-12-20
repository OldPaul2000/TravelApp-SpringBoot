package com.travelapp.travelapp.restcontroller.exceptionhandling.customerrormessage;

public enum PlaceErrorMessages {

    COUNTRY_NOT_FOUND("Country not found"),
    CITY_NOT_FOUND("City not found"),
    COMMUNE_NOT_FOUND("Commune not found"),
    VILLAGE_NOT_FOUND("Village not found"),

    ALREADY_EXISTING_COUNTRY("Country already exists"),
    ALREADY_EXISTING_CITY("City already exists"),
    ALREADY_EXISTING_COMMUNE("Commune already exists"),
    ALREADY_EXISTING_VILLAGE("Village already exists");

    private final String message;
    PlaceErrorMessages(String message){
        this.message = message;
    }

    public String message(){
        return this.message;
    }
}
