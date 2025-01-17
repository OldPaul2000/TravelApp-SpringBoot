package com.travelapp.travelapp.repository;

import com.travelapp.travelapp.model.usersposts.CollagePost;
import com.travelapp.travelapp.model.usersposts.PostComment;
import com.travelapp.travelapp.model.usersposts.PostLike;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CollagePostRepositoryImpl implements CollagePostRepository {

    private EntityManager entityManager;
    public CollagePostRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public CollagePost getCollagePostById(long id){
        return entityManager.find(CollagePost.class, id);
    }

    @Override
    public CollagePost getCollageByUserId(long collageId, long userId){
        TypedQuery<CollagePost> collageQuery = entityManager.createQuery("SELECT cp FROM CollagePost cp " +
                                                                         "WHERE cp.id = :collageId AND " +
                                                                         "cp.user.id = :userId", CollagePost.class);
        collageQuery.setParameter("collageId", collageId);
        collageQuery.setParameter("userId", userId);

        return collageQuery.getSingleResult();
    }

    @Override
    public List<CollagePost> getCollagePostsByUserId(long userId){
        TypedQuery<CollagePost> collageQuery = entityManager.createQuery("SELECT cp FROM CollagePost cp " +
                                                                         "WHERE cp.user.id = :userId", CollagePost.class);
        collageQuery.setParameter("userId", userId);

        return collageQuery.getResultList();
    }

    @Override
    @Transactional
    public void addNewCollage(CollagePost collagePost) {
        entityManager.persist(collagePost);
    }

    @Override
    @Transactional
    public void updateCollage(CollagePost collagePost) {
        entityManager.merge(collagePost);
    }

    @Override
    @Transactional
    public void deleteCollage(CollagePost collagePost) {
        entityManager.remove(collagePost);
    }

    @Override
    @Transactional
    public void removeCollageLike(PostLike postLike){
        entityManager.remove(postLike);
    }

    @Override
    @Transactional
    public void addCollageLike(PostLike postLike){
        entityManager.persist(postLike);
    }

    @Override
    @Transactional
    public void addCollageComment(PostComment postComment){
        entityManager.persist(postComment);
    }

    @Override
    public PostComment getCollageComment(long userId, long commentId){
        TypedQuery<PostComment> query = entityManager.createQuery("SELECT pc FROM PostComment pc " +
                                                                  "WHERE pc.user.id = :userId AND " +
                                                                  "pc.id = :commentId", PostComment.class);
        query.setParameter("userId", userId);
        query.setParameter("commentId", commentId);

        return query.getSingleResult();
    }

    @Override
    public List<PostComment> getCollageComments(long collageId){
        TypedQuery<PostComment> query = entityManager.createQuery("SELECT pc FROM PostComment pc " +
                                                                  "WHERE pc.collagePost.id = :id", PostComment.class);
        query.setParameter("id", collageId);

        return query.getResultList();
    }

    @Override
    @Transactional
    public void deletePostComment(PostComment postComment){
        entityManager.remove(postComment);
    }

    @Override
    public long getCollageCommentsCount(long collageId){
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(pc) FROM PostComment pc " +
                                                           "WHERE pc.collagePost.id = :id", Long.class);
        query.setParameter("id", collageId);

        return query.getSingleResult();
    }

    @Override
    public PostLike getPostLike(long userId, long collageId){
        TypedQuery<PostLike> likeQuery = entityManager.createQuery("SELECT pl FROM PostLike pl " +
                                                                   "WHERE pl.user.id = :userId AND " +
                                                                   "pl.collagePost.id = :collageId", PostLike.class);
        likeQuery.setParameter("userId", userId);
        likeQuery.setParameter("collageId", collageId);

        return likeQuery.getSingleResult();
    }

    @Override
    public List<PostLike> getPostLikes(long collageId){
        TypedQuery<PostLike> likeQuery = entityManager.createQuery("SELECT pl FROM PostLike pl " +
                                                                   "WHERE pl.collagePost.id = :collageId", PostLike.class);
        likeQuery.setParameter("collageId", collageId);

        return likeQuery.getResultList();
    }

    @Override
    public long getCollageLikesCount(long collageId){
        TypedQuery<Long> likeQuery = entityManager.createQuery("SELECT COUNT(pl) FROM PostLike pl " +
                                                               "WHERE pl.collagePost.id = :data", Long.class);
        likeQuery.setParameter("data", collageId);

        return likeQuery.getSingleResult();
    }
}
