package com.travelapp.travelapp.restcontroller.exceptionhandling.places.commune;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommuneRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CommuneErrorResponse> handleCommuneNotFound(CommuneNotFoundException exc){

        CommuneErrorResponse response = new CommuneErrorResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exc.getMessage());
        response.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<CommuneErrorResponse> handleAlreadyExistingCommune(CommuneAlreadyExistsException exc){

        CommuneErrorResponse response = new CommuneErrorResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exc.getMessage());
        response.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
