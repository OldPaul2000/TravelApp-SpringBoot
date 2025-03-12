package com.travelapp.travelapp.securityexceptionhandling;

public class NotEnoughPrivilegesException extends RuntimeException{

    public NotEnoughPrivilegesException(String message) {
        super(message);
    }

    public NotEnoughPrivilegesException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughPrivilegesException(Throwable cause) {
        super(cause);
    }
}
