package com.travelapp.travelapp.repository;

import com.travelapp.travelapp.model.usersposts.Collage;
import com.travelapp.travelapp.model.usersposts.CollageComment;
import com.travelapp.travelapp.model.usersposts.CollageLike;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CollageRepositoryImpl implements CollageRepository {

    private EntityManager entityManager;
    public CollageRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Collage findCollageById(long id){
        return entityManager.find(Collage.class, id);
    }

    @Override
    public List<Collage> findCollagesByUserId(long id){
        TypedQuery<Collage> query = entityManager.createQuery("SELECT cp FROM Collage cp " +
                "WHERE cp.user.id = :id", Collage.class);
        query.setParameter("id", id);

        return query.getResultList();
    }

    @Override
    @Transactional
    public void persistNewCollage(Collage collage) {
        entityManager.persist(collage);
    }

    @Override
    @Transactional
    public void removeCollage(Collage collage) {
        entityManager.remove(collage);
    }

    @Override
    @Transactional
    public void persistNewCollageComment(CollageComment collageComment){
        entityManager.persist(collageComment);
    }
    @Override
    public Collage findCollageByCollageAndUserId(long collageId, long userId){
        TypedQuery<Collage> query = entityManager.createQuery("SELECT cp FROM Collage cp " +
                "WHERE cp.id = :collageId AND " +
                "cp.user.id = :userId", Collage.class);
        query.setParameter("collageId", collageId);
        query.setParameter("userId", userId);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void removeCollageComment(CollageComment collageComment){
        entityManager.remove(collageComment);
    }
    @Override
    public CollageComment findCollageComment(long userId, long commentId){
        TypedQuery<CollageComment> query = entityManager.createQuery("SELECT cc FROM CollageComment cc " +
                                                                    "WHERE cc.user.id = :userId AND " +
                                                                    "cc.id = :commentId", CollageComment.class);
        query.setParameter("userId", userId);
        query.setParameter("commentId", commentId);

        return query.getSingleResult();
    }

    @Override
    public List<CollageComment> findCollageComments(long collageId){
        TypedQuery<CollageComment> query = entityManager.createQuery("SELECT pc FROM CollageComment pc " +
                "WHERE pc.collagePost.id = :id", CollageComment.class);
        query.setParameter("id", collageId);

        return query.getResultList();
    }

    @Override
    public long findCollageCommentsCount(long collageId){
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(pc) FROM CollageComment pc " +
                "WHERE pc.collagePost.id = :id", Long.class);
        query.setParameter("id", collageId);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void mergeCollage(Collage collage) {
        entityManager.merge(collage);
    }

    @Override
    @Transactional
    public void persistNewCollageLike(CollageLike collageLike){
        entityManager.persist(collageLike);
    }

    @Override
    @Transactional
    public void removeCollageLike(CollageLike collageLike){
        entityManager.remove(collageLike);
    }
    @Override
    public CollageLike findCollageLike(long userId, long collageId){
        TypedQuery<CollageLike> query = entityManager.createQuery("SELECT pl FROM CollageLike pl " +
                                                                   "WHERE pl.user.id = :userId AND " +
                                                                   "pl.collagePost.id = :collageId", CollageLike.class);
        query.setParameter("userId", userId);
        query.setParameter("collageId", collageId);

        return query.getSingleResult();
    }

    @Override
    public List<CollageLike> findCollageLikes(long collageId){
        TypedQuery<CollageLike> query = entityManager.createQuery("SELECT pl FROM CollageLike pl " +
                                                                   "WHERE pl.collagePost.id = :id", CollageLike.class);
        query.setParameter("id", collageId);

        return query.getResultList();
    }

    @Override
    public long findCollageLikesCount(long collageId){
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(pl) FROM CollageLike pl " +
                                                               "WHERE pl.collagePost.id = :id", Long.class);
        query.setParameter("id", collageId);

        return query.getSingleResult();
    }
}
