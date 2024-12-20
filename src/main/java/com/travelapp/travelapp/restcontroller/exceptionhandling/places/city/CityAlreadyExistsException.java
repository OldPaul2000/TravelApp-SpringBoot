package com.travelapp.travelapp.restcontroller.exceptionhandling.places.city;

public class CityAlreadyExistsException extends RuntimeException{

    public CityAlreadyExistsException(String message) {
        super(message);
    }

    public CityAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CityAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
