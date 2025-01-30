package com.travelapp.travelapp.service;

import com.travelapp.travelapp.dto.userrelated.*;
import com.travelapp.travelapp.model.postedpictures.TouristicPicture;
import com.travelapp.travelapp.model.userrelated.ProfilePicture;
import com.travelapp.travelapp.model.userrelated.Role;
import com.travelapp.travelapp.model.userrelated.User;
import com.travelapp.travelapp.model.userrelated.UserInfo;
import com.travelapp.travelapp.repository.CollageRepository;
import com.travelapp.travelapp.repository.PictureRepository;
import com.travelapp.travelapp.repository.UserRepository;
import com.travelapp.travelapp.restcontroller.exceptionhandling.users.UserGeneralException;
import com.travelapp.travelapp.restcontroller.exceptionhandling.users.UserNotFoundException;
import com.travelapp.travelapp.restcontroller.exceptionhandling.users.UserRegistrationException;
import com.travelapp.travelapp.security.Roles;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.travelapp.travelapp.restcontroller.exceptionhandling.customerrormessage.UserErrorMessages.*;

@Service
public class UserService {

    private UserRepository userRepository;
    private PictureRepository pictureRepository;
    private CollageRepository collageRepository;

    private PicturePlaceRemovalHelper picturePlaceRemovalHelper;

    @Autowired
    public UserService(EntityManager entityManager,
                       UserRepository userRepository,
                       PictureRepository pictureRepository,
                       CollageRepository collageRepository,
                       PicturePlaceRemovalHelper picturePlaceRemovalHelper) {
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.collageRepository = collageRepository;
        this.picturePlaceRemovalHelper = picturePlaceRemovalHelper;
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
    public void deleteUserAccount(long userId, boolean userPicturesDelete){
        User user = userRepository.findUserById(userId);

        user.getPictureLikes().clear();
        user.getPictureComments().clear();

        user.getCollageLikes().clear();
        user.getCollageComments().clear();
        user.getCollagePosts().clear();

        if(!userPicturesDelete){
            user.getTouristicPictures().forEach(picture -> {
                picture.setUser(null);
            });
        }
        else {
            List<TouristicPicture> touristicPictures = pictureRepository.findTouristicPicturesByUserId(userId);
            touristicPictures.forEach(picture -> {
                picture.getUser().getTouristicPictures().remove(picture);
                picture.setUser(null);

                picturePlaceRemovalHelper.removePlaceFromPicture(picture.getPicturePlace());

                pictureRepository.removePicture(picture);
            });

            // ======================================= Bad code. Try to fix it later =======================================

//            user.getTouristicPictures().forEach(picture -> {
//                picture.getPictureLikes().clear();
//                picture.getPictureComments().clear();
//                picture.getCollagePosts().clear();
//                picture.setCoordinates(null);
//                picture.setUser(null);
//                System.out.println("Picture place:");
//                System.out.println("Picture id:" + picture.getId());
//                System.out.println(picture.getPicturePlace());
//                System.out.println(picture.getPicturePlace().getTouristicPicture().getId());
//                System.out.println("=========================================================================================================");
//                picture.setPicturePlace(null);
//            });
//            user.setTouristicPictures(null);

            // ======================================= Bad code. Try to fix it later =======================================
        }

        user.getRoles().clear();
        user.getUserInfo().setUser(null);
        user.setUserInfo(null);

        userRepository.removeUser(user);
    }

}
