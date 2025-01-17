package com.travelapp.travelapp.restcontroller.exceptionhandling.touristicpictures;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TouristicPictureRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<TouristicPictureErrorResponse> handlerPictureNotFound(TouristicPictureNotFoundException exc){

        TouristicPictureErrorResponse errorResponse = new TouristicPictureErrorResponse();

        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<TouristicPictureErrorResponse> handlerPictureNotFound(PictureAlreadyLikedException exc){

        TouristicPictureErrorResponse errorResponse = new TouristicPictureErrorResponse();

        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
