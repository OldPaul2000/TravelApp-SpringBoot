package com.travelapp.travelapp.restcontroller.exceptionhandling.users;

public class UserGeneralException extends RuntimeException{

    public UserGeneralException(String message) {
        super(message);
    }

    public UserGeneralException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserGeneralException(Throwable cause) {
        super(cause);
    }
}
