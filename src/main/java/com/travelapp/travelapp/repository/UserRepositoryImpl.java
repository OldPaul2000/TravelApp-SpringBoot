package com.travelapp.travelapp.repository;

import com.travelapp.travelapp.model.userrelated.Role;
import com.travelapp.travelapp.model.userrelated.User;
import com.travelapp.travelapp.model.userrelated.UserInfo;
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

     /**
      * Only for testing
      */
    @Override
    public User findUserByIdWithTouristicPictures(long id){
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u " +
                                                           "LEFT JOIN FETCH u.touristicPictures " +
                                                           "WHERE u.id = :id", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public User findUserByUsername(String username){
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u " +
                                                           "WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    @Override
    public User findUserByUsernameWithRoles(String username){
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u " +
                                                           "JOIN FETCH u.roles r " +
                                                           "WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void persistNewUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User findUserByIdWithInfoAndRoles(long id){
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u " +
                "JOIN FETCH u.roles " +
                "JOIN FETCH u.userInfo " +
                "WHERE u.id = :id", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public User findUserById(long id){
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void removeUser(User user) {
        entityManager.remove(user);
    }

    @Override
    @Transactional
    public void mergeUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void removeUserInfo(UserInfo userInfo) {
        entityManager.remove(userInfo);
    }

    @Override
    public void removeRole(Role role) {
        entityManager.remove(role);
    }
}

