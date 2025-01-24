package com.travelapp.travelapp.service;

import com.travelapp.travelapp.dto.userrelated.*;
import com.travelapp.travelapp.model.userrelated.ProfilePicture;
import com.travelapp.travelapp.model.userrelated.Role;
import com.travelapp.travelapp.model.userrelated.User;
import com.travelapp.travelapp.model.userrelated.UserInfo;
import com.travelapp.travelapp.repository.UserRepository;
import com.travelapp.travelapp.restcontroller.exceptionhandling.users.UserGeneralException;
import com.travelapp.travelapp.restcontroller.exceptionhandling.users.UserNotFoundException;
import com.travelapp.travelapp.restcontroller.exceptionhandling.users.UserRegistrationException;
import com.travelapp.travelapp.security.Roles;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.travelapp.travelapp.restcontroller.exceptionhandling.customerrormessage.UserErrorMessages.*;

@Service
public class UserService {

    private UserRepository userRepository;
    private PictureService pictureService;

    public UserService(UserRepository userRepository,
                       PictureService pictureService) {
        this.userRepository = userRepository;
        this.pictureService = pictureService;
    }

    /* Works */
    public UserAndInfoDTOGet getUserByIdWithInfoAndRoles(int id){
        try{
            User user = userRepository.findUserByIdWithInfoAndRoles(id);
            UserInfo info = user.getUserInfo();
            ProfilePicture picture = info.getProfilePicture();

            ProfilePictureDTOGet profilePictureDTO = new ProfilePictureDTOGet(
                    picture.getId(),
                    picture.getFileName()
            );

            UserInfoDTOGet userInfoDTO = new UserInfoDTOGet(
                    info.getFirstName(),
                    info.getLastName(),
                    info.getEmail(),
                    info.getBirthDate(),
                    info.getRegistrationDate(),
                    profilePictureDTO
            );

            UserAndInfoDTOGet userDTO = new UserAndInfoDTOGet(
                    user.getId(),
                    user.getRoles(),
                    userInfoDTO
            );

            return userDTO;
        }
        catch (EmptyResultDataAccessException e){
            throw new UserNotFoundException(USER_NOT_FOUND.message());
        }
    }

    /* Works */
    public void registerUser(UserDTORegister userDTORegister){
        User user = new User();
        user.setUsername(userDTORegister.username());
        // Password needs to be encrypted before is set to the user
        user.setPassword(userDTORegister.password());
        user.setEnabled((byte)1);

        Role role = new Role(Roles.USER.name());
        role.setUser(user);
        user.addRole(role);

        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName(userDTORegister.userInfo().firstName());
        userInfo.setLastName(userDTORegister.userInfo().lastName());
        userInfo.setEmail(userDTORegister.userInfo().email());
        userInfo.setBirthDate(userDTORegister.userInfo().birthDate());
        userInfo.setRegistrationDate(LocalDateTime.now());

        userInfo.setUser(user);
        user.setUserInfo(userInfo);

        ProfilePicture profilePicture = new ProfilePicture();
        profilePicture.setFileName(userDTORegister.userInfo().profilePicture().fileName());

        userInfo.setProfilePicture(profilePicture);

        try{
            userRepository.persistNewUser(user);
        }
        catch (DataIntegrityViolationException e){
            System.out.println(e);
            throw new UserRegistrationException(ALREADY_EXISTING_USER.message());
        }
    }

    /* Works */
    public void updateProfilePicture(int userId, ProfilePictureDTOPost profilePicture){
        try{
            User user = userRepository.findUserByIdWithInfoAndRoles(userId);
            user.getUserInfo()
                    .getProfilePicture()
                    .setFileName(profilePicture.fileName());
            userRepository.mergeUser(user);
        }
        catch (EmptyResultDataAccessException e){
            throw new UserNotFoundException(USER_NOT_FOUND.message());
        }
    }

    /* Works */
    public void updateUserInfo(int userId, UserInfoDTOUpdate userInfoDTOUpdate){
        try{
            User userToUpdate = userRepository.findUserById(userId);

            UserInfo userInfo = userToUpdate.getUserInfo();
            userInfo.setFirstName(userInfoDTOUpdate.firstName());
            userInfo.setLastName(userInfoDTOUpdate.lastName());
            userInfo.setEmail(userInfoDTOUpdate.email());
            userInfo.setBirthDate(userInfoDTOUpdate.birthDate());

            userRepository.mergeUser(userToUpdate);
        }
        catch (Exception e){
            throw new UserGeneralException(USER_UPDATE_ERROR.message());
        }
    }


    // Not finished
    @Transactional
    public void deleteUserAccount(long userId, boolean userPicturesDelete){
        User user = userRepository.findUserById(userId);


        UserInfo userInfo = user.getUserInfo();
        userInfo.setProfilePicture(null);
        List<Role> roles = user.getRoles();
        roles.forEach(role -> role.setUser(null));
        user.setRoles(null);
        user.setUserInfo(null);
        userInfo.setUser(null);

        user.getPictureLikes().forEach(pictureLike -> pictureLike.setUser(null));
        user.setPictureLikes(null);
        user.getPictureComments().forEach(pictureComment -> pictureComment.setUser(null));
        user.setPictureComments(null);
        user.getCollagePosts().forEach(collagePost -> collagePost.setUser(null));
        user.setCollagePosts(null);
        user.getCollageLikes().forEach(collageLike -> collageLike.setUser(null));
        user.setCollageLikes(null);
        user.getCollageComments().forEach(collageComment -> collageComment.setUser(null));
        user.setCollageComments(null);



        userRepository.removeUser(user);
    }



}
