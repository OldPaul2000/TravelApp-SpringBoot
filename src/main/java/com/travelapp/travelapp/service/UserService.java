package com.travelapp.travelapp.service;

import com.travelapp.travelapp.constants.JWTConstants;
import com.travelapp.travelapp.constants.Roles;
import com.travelapp.travelapp.dto.mappers.LoginResponseMapper;
import com.travelapp.travelapp.dto.userrelated.*;
import com.travelapp.travelapp.model.login.LoginRequestDTO;
import com.travelapp.travelapp.model.login.LoginResponseDTO;
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
import com.travelapp.travelapp.securityexceptionhandling.UserNotMatchingException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.travelapp.travelapp.restcontroller.exceptionhandling.customerrormessage.UserErrorMessages.*;
import static com.travelapp.travelapp.securityexceptionhandling.SecurityErrorMessages.USER_NOT_MATCHING;

@Service
public class UserService {

    private LoginResponseMapper loginResponseMapper;
    private FileStorageService fileStorageService;
    private UserPrivilegesVerifier userPrivilegesVerifier;
    private JWTService jwtService;
    private JWTConstants jwtConstants;

    private UserRepository userRepository;
    private PictureRepository pictureRepository;
    private CollageRepository collageRepository;

    private PicturePlaceRemovalHelper picturePlaceRemovalHelper;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(LoginResponseMapper loginResponseMapper,
                       FileStorageService fileStorageService,
                       UserPrivilegesVerifier userPrivilegesVerifier,
                       JWTService jwtService,
                       JWTConstants jwtConstants,
                       UserRepository userRepository,
                       PictureRepository pictureRepository,
                       CollageRepository collageRepository,
                       PicturePlaceRemovalHelper picturePlaceRemovalHelper,
                       PasswordEncoder passwordEncoder) {
        this.loginResponseMapper = loginResponseMapper;
        this.fileStorageService = fileStorageService;
        this.userPrivilegesVerifier = userPrivilegesVerifier;
        this.jwtService = jwtService;
        this.jwtConstants = jwtConstants;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.collageRepository = collageRepository;
        this.picturePlaceRemovalHelper = picturePlaceRemovalHelper;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${jwt.secret-key}")
    private String JWT_SECRET_KEY;

    @Value("${app.files.profile-picture}")
    private String PROFILE_PICTURE_LOCATION;

    @Value("${app.files.touristic-pictures}")
    private String TOURISTIC_PICTURE_LOCATION;

    public LoginResponseDTO loginUserJwt(LoginRequestDTO loginRequest,
                                             AuthenticationManager authenticationManager){
        String jwt;
        LoginResponseDTO loginResponseDTO = null;
        Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(),
                loginRequest.password());
        Authentication authenticationResponse = authenticationManager.authenticate(authentication);
        if(authenticationResponse != null && authenticationResponse.isAuthenticated()){
            User user = userRepository.findUserByUsername(authenticationResponse.getName());

            String secret = jwtConstants.getSECRET_KEY();
            SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
            jwt = Jwts.builder().issuer("Travel App").subject("JWT Token")
                    .claim("username", authenticationResponse.getName())
                    .claim("authorities", authenticationResponse.getAuthorities().stream().map(
                            GrantedAuthority::getAuthority
                    ).collect(Collectors.joining(",")))
                    .claim("userId", user.getId())
                    .issuedAt(new Date())
                    .expiration(new Date((new Date()).getTime() + 600000))
                    .signWith(secretKey).compact();

            jwtService.addNewToken(user, jwt);

            loginResponseDTO = loginResponseMapper.toDTO(user, jwt);
        }
        return loginResponseDTO;
    }

    // Only for app maintenance(only used by OWNER or ADMIN, not used for regular app usage)
    public UserAndInfoDTOGet getUserByIdWithInfoAndRoles(long id){
        try{
            User user = userRepository.findUserByIdWithInfoAndRoles(id);
            UserInfo info = user.getUserInfo();
            ProfilePicture picture = info.getProfilePicture();

            ProfilePictureDTOGet profilePictureDTO = null;
            if(picture != null){
                profilePictureDTO = new ProfilePictureDTOGet(
                        picture.getId(),
                        picture.getFileName()
                );
            }


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

    public void registerUser(UserDTORegister userDTORegister){
        User user = new User();
        user.setUsername(userDTORegister.username());
        user.setPassword(passwordEncoder.encode(userDTORegister.password()));
        user.setEnabled((byte)1);

        for(int i = 0; i < userDTORegister.roles().length; i++){
            String dtoRole = userDTORegister.roles()[i];
            if(Roles.exists(dtoRole)){
                Role role = new Role("ROLE_" + dtoRole);
                role.setUser(user);
                user.addRole(role);
            }
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName(userDTORegister.userInfo().firstName());
        userInfo.setLastName(userDTORegister.userInfo().lastName());
        userInfo.setEmail(userDTORegister.userInfo().email());
        userInfo.setBirthDate(userDTORegister.userInfo().birthDate());
        userInfo.setRegistrationDate(LocalDateTime.now());

        userInfo.setUser(user);
        user.setUserInfo(userInfo);

        try{
            userRepository.persistNewUser(user);
        }
        catch (DataIntegrityViolationException e){
            throw new UserRegistrationException(ALREADY_EXISTING_USER.message());
        }
    }

    public void updateProfilePicture(long userId, MultipartFile file){
        try{
            User user = userRepository.findUserByIdWithInfoAndRoles(userId);
            if(!userPrivilegesVerifier.isCurrentUser(user.getUsername())){
                throw new UserNotMatchingException(USER_NOT_MATCHING.message());
            }
            ProfilePicture picture = user.getUserInfo().getProfilePicture();
            if(picture == null){
                picture = new ProfilePicture();
                picture.setFileName(file.getOriginalFilename());

                user.getUserInfo().setProfilePicture(picture);
            }
            else{
                fileStorageService.deleteFile(userId,
                        PROFILE_PICTURE_LOCATION,
                        picture.getFileName());
                picture.setFileName(file.getOriginalFilename());
            }

            fileStorageService.storeFile(userId,
                    PROFILE_PICTURE_LOCATION,
                    picture.getFileName(),
                    file.getBytes());

            userRepository.mergeUser(user);
        }
        catch (EmptyResultDataAccessException e){
            throw new UserNotFoundException(USER_NOT_FOUND.message());
        }
        catch (IOException e){
            System.out.println("Setting profile picture failed");
        }
    }

    public void updateUserInfo(long userId, UserInfoDTOUpdate userInfoDTOUpdate){
        try{
            User user = userRepository.findUserById(userId);
            if(!userPrivilegesVerifier.isCurrentUser(user.getUsername())){
                throw new UserNotMatchingException(USER_NOT_MATCHING.message());
            }

            UserInfo userInfo = user.getUserInfo();
            userInfo.setFirstName(userInfoDTOUpdate.firstName());
            userInfo.setLastName(userInfoDTOUpdate.lastName());
            userInfo.setEmail(userInfoDTOUpdate.email());
            userInfo.setBirthDate(userInfoDTOUpdate.birthDate());

            userRepository.mergeUser(user);
        }
        catch (Exception e){
            throw new UserGeneralException(USER_UPDATE_ERROR.message());
        }
    }

    public void deleteUserAccount(long userId, boolean userPicturesDelete){
        User user = userRepository.findUserById(userId);
        if(!userPrivilegesVerifier.isCurrentUser(user.getUsername())){
            throw new UserNotMatchingException(USER_NOT_MATCHING.message());
        }

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
            removeTouristicPictures(userId);
        }

        removeProfilePicture(userId, user);

        user.getRoles().clear();
        user.getUserInfo().setUser(null);
        user.setUserInfo(null);

        userRepository.removeUser(user);
    }

    private void removeProfilePicture(long userId, User user){
        String profilePicture = user.getUserInfo().getProfilePicture().getFileName();
        if(profilePicture != null){
            fileStorageService.deleteFile(userId,
                    PROFILE_PICTURE_LOCATION,
                    profilePicture);
        }
    }

    private void removeTouristicPictures(long userId){
        List<TouristicPicture> touristicPictures = pictureRepository.findTouristicPicturesByUserId(userId);
        touristicPictures.forEach(picture -> {
            picture.getUser().getTouristicPictures().remove(picture);
            picture.setUser(null);
            picturePlaceRemovalHelper.removePlaceFromPicture(picture.getPicturePlace());

            pictureRepository.removePicture(picture);

            fileStorageService.deleteFile(userId,
                    TOURISTIC_PICTURE_LOCATION,
                    picture.getFileName());
        });
    }

}
