package com.travelapp.travelapp.dto.postedpictures;

import com.travelapp.travelapp.dto.places.PicturePlaceDTOGet;
import com.travelapp.travelapp.model.postedpictures.GpsCoords;

import java.time.LocalDateTime;

public record TouristicPictureDTOGet(
        Long id,
        PostingUserDTOGet user,
        String fileName,
        LocalDateTime captureDateTime,
        String description,
        PicturePlaceDTOGet picturePlace,
        GpsCoords coords,
        long likesCount,
        long commentsCount
) {
}
