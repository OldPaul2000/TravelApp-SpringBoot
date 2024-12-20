package com.travelapp.travelapp.restcontroller;

import com.travelapp.travelapp.dto.userrelated.ProfilePictureDTOPost;
import com.travelapp.travelapp.dto.userrelated.UserDTORegister;
import com.travelapp.travelapp.dto.userrelated.UserInfoDTOUpdate;
import com.travelapp.travelapp.model.userrelated.User;
import com.travelapp.travelapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userById/{id}")
    public ResponseEntity<User> userById(@PathVariable int id){
        User user = userService.getUserByIdWithInfo(id);

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

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
