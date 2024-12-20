package com.travelapp.travelapp.restcontroller.exceptionhandling.users;

public class UserRegistrationException extends RuntimeException{

    public UserRegistrationException(String message) {
        super(message);
    }

    public UserRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserRegistrationException(Throwable cause) {
        super(cause);
    }
}
