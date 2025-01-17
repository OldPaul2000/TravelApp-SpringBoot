package com.travelapp.travelapp.repository;

import com.travelapp.travelapp.model.userrelated.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private EntityManager entityManager;
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Just for testing
    @Override
    public User findUserByIdWithTouristicPictures(long id){
        TypedQuery<User> userQuery = entityManager.createQuery("SELECT u FROM User u " +
                                                               "LEFT JOIN FETCH u.touristicPictures " +
                                                               "WHERE u.id = :data", User.class);
        userQuery.setParameter("data", id);
        return userQuery.getSingleResult();
    }


    @Override
    public User findUserByIdWithInfoAndRoles(long id){
        TypedQuery<User> userQuery = entityManager.createQuery("SELECT u FROM User u " +
                                                               "JOIN FETCH u.roles " +
                                                               "JOIN FETCH u.userInfo " +
                                                               "WHERE u.id = :data", User.class);
        userQuery.setParameter("data", id);
        return userQuery.getSingleResult();
    }

    @Override
    public User findUserById(long id){
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void addNewUser(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        entityManager.remove(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        entityManager.merge(user);
    }
}
