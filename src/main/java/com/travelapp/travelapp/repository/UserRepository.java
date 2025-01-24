package com.travelapp.travelapp.repository;

import com.travelapp.travelapp.model.userrelated.User;

public interface UserRepository {


    // JUST FOR TESTING
    User findUserByIdWithTouristicPictures(long id);

    User findUserByIdWithInfoAndRoles(long id);

    void persistNewUser(User user);

    User findUserById(long id);

    void removeUser(User user);

    void mergeUser(User user);

}
