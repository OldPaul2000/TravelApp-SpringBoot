package com.travelapp.travelapp.restcontroller.exceptionhandling.places.commune;

public class CommuneAlreadyExistsException extends RuntimeException{

    public CommuneAlreadyExistsException(String message) {
        super(message);
    }

    public CommuneAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommuneAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
