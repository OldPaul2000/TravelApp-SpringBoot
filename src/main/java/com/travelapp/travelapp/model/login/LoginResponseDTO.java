package com.travelapp.travelapp.model.login;

import com.travelapp.travelapp.model.userrelated.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record LoginResponseDTO(long userId,
                               List<Role> roles,
                               String firstName,
                               String lastName,
                               String email,
                               LocalDate birthDate,
                               LocalDateTime registrationDate,
                               String profilePictureName,
                               byte[] profilePictureBytes,
                               String jwtToken) {
}
