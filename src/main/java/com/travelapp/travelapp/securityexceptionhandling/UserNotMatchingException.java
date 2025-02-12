package com.travelapp.travelapp.securityexceptionhandling;

public class UserNotMatchingException extends RuntimeException{

    public UserNotMatchingException(String message) {
        super(message);
    }

    public UserNotMatchingException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotMatchingException(Throwable cause) {
        super(cause);
    }
}
