package com.travelapp.travelapp.restcontroller.exceptionhandling.places.commune;

public class CommuneNotFoundException extends RuntimeException{

    public CommuneNotFoundException(String message) {
        super(message);
    }

    public CommuneNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommuneNotFoundException(Throwable cause) {
        super(cause);
    }
}
