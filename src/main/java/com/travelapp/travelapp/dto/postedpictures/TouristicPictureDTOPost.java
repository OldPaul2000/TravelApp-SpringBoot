package com.travelapp.travelapp.dto.postedpictures;

public record TouristicPictureDTOPost(
        long userId,
        String location,
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
