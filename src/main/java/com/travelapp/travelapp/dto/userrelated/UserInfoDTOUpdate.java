package com.travelapp.travelapp.dto.userrelated;

import java.time.LocalDate;

public record UserInfoDTOUpdate(
        String firstName,
        String lastName,
        String email,
        LocalDate birthDate
) {
}
