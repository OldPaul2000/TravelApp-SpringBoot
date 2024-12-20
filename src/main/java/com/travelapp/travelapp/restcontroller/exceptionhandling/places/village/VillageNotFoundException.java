package com.travelapp.travelapp.restcontroller.exceptionhandling.places.village;

public class VillageNotFoundException extends RuntimeException{

    public VillageNotFoundException(String message) {
        super(message);
    }

    public VillageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public VillageNotFoundException(Throwable cause) {
        super(cause);
    }
}
