package com.travelapp.travelapp.restcontroller;

import com.travelapp.travelapp.dto.collagepost.CollageDTOGet;
import com.travelapp.travelapp.dto.userrelated.ProfilePictureDTOPost;
import com.travelapp.travelapp.dto.userrelated.UserAndInfoDTOGet;
import com.travelapp.travelapp.dto.userrelated.UserDTORegister;
import com.travelapp.travelapp.dto.userrelated.UserInfoDTOUpdate;
import com.travelapp.travelapp.model.login.LoginRequestDTO;
import com.travelapp.travelapp.model.login.LoginResponseDTO;
import com.travelapp.travelapp.repository.UserRepository;
import com.travelapp.travelapp.service.CollageService;
import com.travelapp.travelapp.service.UserService;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;
    private CollageService collageService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    public UserController(UserService userService,
                          CollageService collageService,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager,
                          Environment env) {
        this.userService = userService;
        this.collageService = collageService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    /* Works */
    @GetMapping("/{userId}")
    public ResponseEntity<UserAndInfoDTOGet> userById(@PathVariable long userId){
        UserAndInfoDTOGet user = userService.getUserByIdWithInfoAndRoles(userId);

        return ResponseEntity.ok(user);
    }

    /* Works */
    @GetMapping("/collages/{userId}")
    public List<CollageDTOGet> getCollagesFromUser(@PathVariable long userId){
        List<CollageDTOGet> collages = collageService.getCollagesFromUser(userId);
        return collages;
    }

    /* Works */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest){
        String jwt = userService.loginUserWithJwt(loginRequest, authenticationManager);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new LoginResponseDTO(HttpStatus.OK.getReasonPhrase(), jwt));
    }

    /* Works */
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTORegister user){
        userService.registerUser(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /* Works */
    @PutMapping("/profile-pictures/{userId}")
    public ResponseEntity<String> changeProfilePicture(@PathVariable long userId, @RequestBody ProfilePictureDTOPost profilePicture){
            userService.updateProfilePicture(userId, profilePicture);

            return new ResponseEntity<>(HttpStatus.OK);
    }

    /* Works */
    @PutMapping("/user-info/{userId}")
    public ResponseEntity<String> updateUserInfo(@PathVariable long userId, @RequestBody UserInfoDTOUpdate userInfo){
        userService.updateUserInfo(userId, userInfo);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* Works */
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable long userId,
                                             @RequestParam Boolean deletePictures){
        userService.deleteUserAccount(userId, deletePictures);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
