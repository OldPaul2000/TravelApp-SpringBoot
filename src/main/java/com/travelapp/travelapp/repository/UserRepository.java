package com.travelapp.travelapp.repository;

import com.travelapp.travelapp.model.userrelated.User;

public interface UserRepository {


    User findUserByIdWithTouristicPictures(long id);

    User findUserByIdWithInfoAndRoles(long id);

    User findUserById(long id);

    void addNewUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

}
