package com.travelapp.travelapp.restcontroller.exceptionhandling.collageposts;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CollagePostRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CollagePostErrorResponse> handleCollageNotFound(CollagePostNotFoundException exc){

        CollagePostErrorResponse response = new CollagePostErrorResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(exc.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<CollagePostErrorResponse> handleCollageAlreadyLiked(CollagePostAlreadyLikedException exc){

        CollagePostErrorResponse response = new CollagePostErrorResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exc.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
