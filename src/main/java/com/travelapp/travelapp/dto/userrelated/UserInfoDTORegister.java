package com.travelapp.travelapp.dto.userrelated;

import java.time.LocalDate;

public record UserInfoDTORegister(
        String firstName,
        String lastName,
        String email,
        LocalDate birthDate,
        ProfilePictureDTOPost profilePicture
) {
}
