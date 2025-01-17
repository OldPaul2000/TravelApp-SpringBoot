package com.travelapp.travelapp.dto.userrelated;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserInfoDTOGet(
        String firstName,
        String lastName,
        String email,
        LocalDate birthDate,
        LocalDateTime registrationDate,
        ProfilePictureDTOGet profilePicture
) {
}
