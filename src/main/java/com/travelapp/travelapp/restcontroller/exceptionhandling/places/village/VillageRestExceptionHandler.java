package com.travelapp.travelapp.restcontroller.exceptionhandling.places.village;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class VillageRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<VillageErrorResponse> handleVillageNotFound(VillageNotFoundException exc){

        VillageErrorResponse response = new VillageErrorResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exc.getMessage());
        response.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<VillageErrorResponse> handleAlreadyExistingVillage(VillageAlreadyExistsException exc){

        VillageErrorResponse response = new VillageErrorResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exc.getMessage());
        response.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
