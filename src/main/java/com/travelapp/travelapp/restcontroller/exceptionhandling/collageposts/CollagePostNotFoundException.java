package com.travelapp.travelapp.restcontroller.exceptionhandling.collageposts;

public class CollagePostNotFoundException extends RuntimeException{

    public CollagePostNotFoundException(String message) {
        super(message);
    }

    public CollagePostNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CollagePostNotFoundException(Throwable cause) {
        super(cause);
    }
}
