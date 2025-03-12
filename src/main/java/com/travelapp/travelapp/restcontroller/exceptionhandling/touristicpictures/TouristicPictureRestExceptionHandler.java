package com.travelapp.travelapp.restcontroller.exceptionhandling.touristicpictures;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class TouristicPictureRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<TouristicPictureErrorResponse> handlePictureAlreadyExists(FileAlreadyExistsException exc){

        TouristicPictureErrorResponse response = new TouristicPictureErrorResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setTimestamp(LocalDateTime.now());
        response.setMessage(exc.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<TouristicPictureErrorResponse> handlePictureNotFound(TouristicPictureNotFoundException exc){

        TouristicPictureErrorResponse response = new TouristicPictureErrorResponse();

        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(exc.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<TouristicPictureErrorResponse> handlePictureAlreadyLiked(PictureAlreadyLikedException exc){

        TouristicPictureErrorResponse response = new TouristicPictureErrorResponse();

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exc.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
