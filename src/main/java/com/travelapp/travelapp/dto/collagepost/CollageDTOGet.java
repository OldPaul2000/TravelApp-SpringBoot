package com.travelapp.travelapp.dto.collagepost;

import com.travelapp.travelapp.dto.postedpictures.PostingUserDTOGet;
import com.travelapp.travelapp.dto.postedpictures.TouristicPictureDTOGet;

import java.time.LocalDateTime;
import java.util.List;

public record CollageDTOGet(
        Long id,
        String description,
        LocalDateTime dateTime,
        PostingUserDTOGet user,
        List<TouristicPictureDTOGet> pictures
) {
}
