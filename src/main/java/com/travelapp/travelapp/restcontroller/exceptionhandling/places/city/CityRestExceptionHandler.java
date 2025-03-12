package com.travelapp.travelapp.restcontroller.exceptionhandling.places.city;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CityRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CityErrorResponse> handleCityNotFound(CityNotFoundException exc){

        CityErrorResponse response = new CityErrorResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exc.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<CityErrorResponse> handleAlreadyExistingCity(CityAlreadyExistsException exc){

        CityErrorResponse response = new CityErrorResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exc.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
