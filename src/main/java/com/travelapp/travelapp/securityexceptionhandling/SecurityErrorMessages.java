package com.travelapp.travelapp.securityexceptionhandling;

public enum SecurityErrorMessages {

    USER_NOT_MATCHING("The current user doesn't match the introduced user id");

    private final String message;
    SecurityErrorMessages(String message){
        this.message = message;
    }

    public String message(){
        return this.message;
    }

}
