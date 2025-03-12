package com.travelapp.travelapp.securityexceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class UserNotMatchingExceptionHandling {

    @ExceptionHandler
    public ResponseEntity<UserNotMatchingResponse> handleUserNotMatching(UserNotMatchingException exc){

        UserNotMatchingResponse response = new UserNotMatchingResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exc.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
