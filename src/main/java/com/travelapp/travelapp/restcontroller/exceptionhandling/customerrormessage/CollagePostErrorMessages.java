package com.travelapp.travelapp.restcontroller.exceptionhandling.customerrormessage;

public enum CollagePostErrorMessages {

    COLLAGE_POST_NOT_FOUND("Collage not found"),
    COLLAGE_POST_ALREADY_LIKED("You have already liked this post");

    private final String message;
    CollagePostErrorMessages(String message){
        this.message = message;
    }

    public String message(){
        return this.message;
    }

}
