package com.travelapp.travelapp.restcontroller.exceptionhandling.places.country;

import java.time.LocalDateTime;

public class CountryErrorResponse {

    private int status;
    private String message;
    private LocalDateTime timestamp;

    public CountryErrorResponse() {}
    public CountryErrorResponse(int status, String message, LocalDateTime timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
