package com.travelapp.travelapp.dto.postedpictures;

public record TouristicPictureDTOPost(
        String fileName,
        String description,
        String country,
        String city,
        String commune,
        String village,
        String placeName,
        double latitude,
        double longitude
) {
}
