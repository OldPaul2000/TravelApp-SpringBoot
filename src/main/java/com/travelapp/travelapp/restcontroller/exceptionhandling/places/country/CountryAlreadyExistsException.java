package com.travelapp.travelapp.restcontroller.exceptionhandling.places.country;

public class CountryAlreadyExistsException extends RuntimeException{

    public CountryAlreadyExistsException(String message) {
        super(message);
    }

    public CountryAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CountryAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
