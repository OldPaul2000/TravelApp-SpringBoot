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

    // JUST FOR TESTING
    @Override
    public User findUserByIdWithTouristicPictures(long id){
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u " +
                                                           "LEFT JOIN FETCH u.touristicPictures " +
                                                           "WHERE u.id = :id", User.class);
        query.setParameter("id", id);
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
}
