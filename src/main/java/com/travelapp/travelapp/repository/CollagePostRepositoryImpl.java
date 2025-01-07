package com.travelapp.travelapp.repository;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepositoryImpl implements PostRepository{

    private EntityManager entityManager;
    public PostRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


}
