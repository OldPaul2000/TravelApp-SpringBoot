package com.travelapp.travelapp.restcontroller.exceptionhandling.customerrormessage;

public enum PictureErrorMessages {


    PICTURE_ALREADY_EXISTS("You already uploaded this picture"),
    PICTURE_NOT_FOUND("Picture not found"),
    ALREADY_LIKED_PICTURE("You have already liked this picture");

    private final String message;
    PictureErrorMessages(String message){
        this.message = message;
    }

    public String message(){
        return message;
    }

}
