package com.travelapp.travelapp.restcontroller.exceptionhandling.places.country;

public class CountryNotFoundException extends RuntimeException{

    public CountryNotFoundException(String message) {
        super(message);
    }

    public CountryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CountryNotFoundException(Throwable cause) {
        super(cause);
    }
}
