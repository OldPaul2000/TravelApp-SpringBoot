package com.travelapp.travelapp.repository;

import com.travelapp.travelapp.model.userrelated.User;

public interface UserRepository {


    User findUserByIdWithTouristicPictures(int id);

    User findUserByIdWithInfoAndRoles(int id);

    User findUserById(int id);

    void addNewUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

}
