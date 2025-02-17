package com.travelapp.travelapp.repository;

import com.travelapp.travelapp.model.userrelated.Role;
import com.travelapp.travelapp.model.userrelated.User;
import com.travelapp.travelapp.model.userrelated.UserInfo;

public interface UserRepository {


    // JUST FOR TESTING
    User findUserByIdWithTouristicPictures(long id);

    User findUserByUsername(String username);

    User findUserByUsernameWithRoles(String username);

    User findUserByIdWithInfoAndRoles(long id);

    void persistNewUser(User user);

    User findUserById(long id);

    void removeUser(User user);

    void mergeUser(User user);

    void removeUserInfo(UserInfo userInfo);

    void removeRole(Role role);

}
