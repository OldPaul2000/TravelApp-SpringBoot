package com.travelapp.travelapp.restcontroller;

import com.travelapp.travelapp.dto.userrelated.ProfilePictureDTOPost;
import com.travelapp.travelapp.dto.userrelated.UserAndInfoDTOGet;
import com.travelapp.travelapp.dto.userrelated.UserDTORegister;
import com.travelapp.travelapp.dto.userrelated.UserInfoDTOUpdate;
import com.travelapp.travelapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userById/{id}")
    public ResponseEntity<UserAndInfoDTOGet> userById(@PathVariable int id){
        System.out.println("\u001b[31m" + "========================================================================" + "\u001B[0m");
        UserAndInfoDTOGet user = userService.getUserByIdWithInfo(id);
        System.out.println("\u001b[31m" + "========================================================================" + "\u001B[0m");

        return ResponseEntity.ok(user);
    }

    @PutMapping("/updateProfilePicture/{userId}")
    public ResponseEntity<String> updateProfilePicture(@PathVariable int userId, @RequestBody ProfilePictureDTOPost profilePicture){
            userService.updateUserProfilePicture(userId, profilePicture);

            return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateUserInfo/{userId}")
    public ResponseEntity<String> updateUserInfo(@PathVariable int userId, @RequestBody UserInfoDTOUpdate userInfo){
        userService.updateUserInfo(userId, userInfo);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTORegister user){
        userService.registerUser(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
