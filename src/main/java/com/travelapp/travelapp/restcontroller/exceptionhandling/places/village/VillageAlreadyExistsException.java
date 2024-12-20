package com.travelapp.travelapp.restcontroller.exceptionhandling.places.village;

public class VillageAlreadyExistsException extends RuntimeException{

    public VillageAlreadyExistsException(String message) {
        super(message);
    }

    public VillageAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public VillageAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
