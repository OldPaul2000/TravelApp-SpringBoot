package com.travelapp.travelapp.service;

import com.travelapp.travelapp.dto.userrelated.ProfilePictureDTOPost;
import com.travelapp.travelapp.dto.userrelated.UserDTORegister;
import com.travelapp.travelapp.dto.userrelated.UserInfoDTOUpdate;
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

import java.time.LocalDateTime;

import static com.travelapp.travelapp.restcontroller.exceptionhandling.customerrormessage.UserErrorMessages.*;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByIdWithInfo(int id){
        try{
            return userRepository.findUserByIdWithInfoAndRoles(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new UserNotFoundException(USER_NOT_FOUND.message());
        }
    }

    public void updateUserProfilePicture(int userId, ProfilePictureDTOPost profilePicture){
        try{
            User user = userRepository.findUserByIdWithInfoAndRoles(userId);
            user.getUserInfo()
                    .getProfilePicture()
                    .setFileName(profilePicture.fileName());
            userRepository.updateUser(user);
        }
        catch (EmptyResultDataAccessException e){
            throw new UserNotFoundException(USER_NOT_FOUND.message());
        }
    }

    public void updateUserInfo(int userId, UserInfoDTOUpdate userInfoDTOUpdate){
        try{
            User userToUpdate = userRepository.findUserById(userId);

            UserInfo userInfo = userToUpdate.getUserInfo();
            userInfo.setFirstName(userInfoDTOUpdate.firstName());
            userInfo.setLastName(userInfoDTOUpdate.lastName());
            userInfo.setEmail(userInfoDTOUpdate.email());
            userInfo.setBirthDate(userInfoDTOUpdate.birthDate());

            userRepository.updateUser(userToUpdate);
        }
        catch (Exception e){
            throw new UserGeneralException(USER_UPDATE_ERROR.message());
        }
    }

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
            userRepository.addNewUser(user);
        }
        catch (DataIntegrityViolationException e){
            System.out.println(e);
            throw new UserRegistrationException(ALREADY_EXISTING_USER.message());
        }
    }



}
