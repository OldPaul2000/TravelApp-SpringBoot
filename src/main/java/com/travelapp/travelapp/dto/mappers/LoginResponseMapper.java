package com.travelapp.travelapp.dto.mappers;

import com.travelapp.travelapp.model.login.LoginResponseDTO;
import com.travelapp.travelapp.model.userrelated.User;
import com.travelapp.travelapp.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LoginResponseMapper {

    @Value("${app.files.profile-picture}")
    private String PROFILE_PICTURE_LOCATION;

    private FileStorageService fileStorageService;

    public LoginResponseMapper(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    public LoginResponseDTO toDTO(User user, String jwt){
        String profilePictureName = null;
        if(user.getUserInfo().getProfilePicture() != null){
            profilePictureName = user.getUserInfo().getProfilePicture().getFileName();
        }
        return new LoginResponseDTO(
                user.getId(),
                user.getRoles(),
                user.getUserInfo().getFirstName(),
                user.getUserInfo().getLastName(),
                user.getUserInfo().getEmail(),
                user.getUserInfo().getBirthDate(),
                user.getUserInfo().getRegistrationDate(),
                profilePictureName,
                fileStorageService.getFileBytes(
                        user.getId(),
                        PROFILE_PICTURE_LOCATION,
                        profilePictureName),
                jwt
        );
    }

}
