package com.travelapp.travelapp.dto.collagepost;

import com.travelapp.travelapp.dto.postedpictures.PostingUserDTOGet;

public record CollageLikeDTOGet(
        Long id,
        PostingUserDTOGet user
) {
}
