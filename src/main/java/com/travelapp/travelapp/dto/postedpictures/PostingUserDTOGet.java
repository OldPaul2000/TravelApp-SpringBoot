package com.travelapp.travelapp.dto.postedpictures;

// The user DTO model which will appear on user's posts like
// touristic pictures, likes, comments, collage posts.
// The user id is for other users to be able to access other users' profile
public record PostingUserDTOGet(
        Long id,
        String firstName,
        String lastName,
        String profilePicture
) {
}
