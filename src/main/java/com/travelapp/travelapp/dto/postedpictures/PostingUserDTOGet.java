package com.travelapp.travelapp.dto.postedpictures;

// The user DTO model which will appear on user's posted picture(first name and last name, profile picture,
// and the user's id to be able to access the user's profile)
public record PictureUserDTOGet(
        Long id,
        String firstName,
        String lastName,
        String profilePicture
) {
}
