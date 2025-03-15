package com.travelapp.travelapp.restcontroller;

import com.travelapp.travelapp.dto.collagepost.CollageDTOGet;
import com.travelapp.travelapp.dto.userrelated.UserAndInfoDTOGet;
import com.travelapp.travelapp.dto.userrelated.UserDTORegister;
import com.travelapp.travelapp.dto.userrelated.UserInfoDTOUpdate;
import com.travelapp.travelapp.model.login.LoginRequestDTO;
import com.travelapp.travelapp.model.login.LoginResponseDTO;
import com.travelapp.travelapp.service.CollageService;
import com.travelapp.travelapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;
    private CollageService collageService;
    private AuthenticationManager authenticationManager;

    public UserController(UserService userService,
                          CollageService collageService,
                          AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.collageService = collageService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserAndInfoDTOGet> getUserById(@PathVariable long userId){
        UserAndInfoDTOGet user = userService.getUserByIdWithInfoAndRoles(userId);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/collages/{userId}")
    public List<CollageDTOGet> getCollagesFromUser(@PathVariable long userId,
                                                   @RequestParam int startPage,
                                                   @RequestParam int offset){
        List<CollageDTOGet> collages = collageService.getCollagesFromUser(userId, startPage, offset);
        return collages;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest){
        LoginResponseDTO loginResponse = userService.loginUserJwt(loginRequest, authenticationManager);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTORegister userDTO){
        userService.registerUser(userDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/profile-pictures/{userId}")
    public ResponseEntity<String> changeProfilePicture(@PathVariable long userId,
                                                       @RequestParam MultipartFile file){
            userService.updateProfilePicture(userId, file);

            return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/user-info/{userId}")
    public ResponseEntity<String> updateUserInfo(@PathVariable long userId, @RequestBody UserInfoDTOUpdate userInfo){
        userService.updateUserInfo(userId, userInfo);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable long userId,
                                             @RequestParam Boolean deletePictures){
        userService.deleteUserAccount(userId, deletePictures);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
