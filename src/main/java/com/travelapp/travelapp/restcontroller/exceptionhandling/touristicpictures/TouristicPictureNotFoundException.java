package com.travelapp.travelapp.restcontroller.exceptionhandling.touristicpictures;

public class TouristicPictureNotFoundException extends RuntimeException{

    public TouristicPictureNotFoundException(String message) {
        super(message);
    }

    public TouristicPictureNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TouristicPictureNotFoundException(Throwable cause) {
        super(cause);
    }
}
