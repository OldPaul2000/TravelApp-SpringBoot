package com.travelapp.travelapp.dto.collagepost;

import com.travelapp.travelapp.dto.postedpictures.PostingUserDTOGet;

import java.time.LocalDateTime;

public record CollageCommentDTOGet(
        Long id,
        String comment,
        LocalDateTime dateTime,
        PostingUserDTOGet user,
        boolean edited
) {
}
