package com.travelapp.travelapp.restcontroller.exceptionhandling.customerrormessage;

public enum UserErrorMessages {

    USER_NOT_FOUND("User not found"),
    USER_UPDATE_ERROR("Error updating user"),
    ALREADY_EXISTING_USER("User already exists");

    private final String message;
    UserErrorMessages(String message){
        this.message = message;
    }

    public String message(){
        return this.message;
    }

}
