package com.travelapp.travelapp.repository;

import com.travelapp.travelapp.model.security.JWT;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JWTRepositoryImpl implements JWTRepository{

    private EntityManager entityManager;

    public JWTRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public JWT findTokenByUserId(long userId) {
        TypedQuery<JWT> query = entityManager.createQuery("SELECT j FROM JWT j " +
                                                          "WHERE j.user.id= :userId", JWT.class);
        query.setParameter("userId", userId);

        try{
            return query.getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional
    public void persistToken(JWT jwt) {
        entityManager.persist(jwt);
    }

    @Override
    @Transactional
    public void mergeToken(JWT jwt){
        entityManager.merge(jwt);
    }

    @Override
    @Transactional
    public void removeToken(JWT jwt){
        entityManager.remove(jwt);
    }
}
