package com.travelapp.travelapp.dto.postedpictures;

import java.time.LocalDateTime;

public record PictureCommentDTOGet(
        Long id,
        String comment,
        LocalDateTime dateTime,
        PostingUserDTOGet user,
        boolean edited
) {
}
