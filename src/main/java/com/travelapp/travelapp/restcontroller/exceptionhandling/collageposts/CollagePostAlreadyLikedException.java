package com.travelapp.travelapp.restcontroller.exceptionhandling.collageposts;

public class CollagePostAlreadyLikedException extends RuntimeException{

    public CollagePostAlreadyLikedException(String message) {
        super(message);
    }

    public CollagePostAlreadyLikedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CollagePostAlreadyLikedException(Throwable cause) {
        super(cause);
    }
}
