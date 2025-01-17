package com.travelapp.travelapp.restcontroller.exceptionhandling.touristicpictures;

public class PictureAlreadyLikedException extends RuntimeException{

    public PictureAlreadyLikedException(String message){
        super(message);
    }

    public PictureAlreadyLikedException(String message, Throwable cause){
        super(message,cause);
    }

    public PictureAlreadyLikedException(Throwable cause){
        super(cause);
    }

}
