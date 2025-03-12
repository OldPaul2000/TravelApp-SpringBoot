package com.travelapp.travelapp.restcontroller.exceptionhandling.places.country;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CountryRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CountryErrorResponse> handleCountryNotFound(CountryNotFoundException exc){

        CountryErrorResponse response = new CountryErrorResponse();

        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(exc.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<CountryErrorResponse> handleCountryAlreadyExists(CountryAlreadyExistsException exc){

        CountryErrorResponse response = new CountryErrorResponse();

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exc.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
