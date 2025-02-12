package com.travelapp.travelapp.repository;

import com.travelapp.travelapp.model.postedpictures.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PictureRepositoryImpl implements PictureRepository {

    private EntityManager entityManager;

    public PictureRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<TouristicPicture> findTouristicPicturesByUser(long id) {
        TypedQuery<TouristicPicture> query = entityManager.createQuery("SELECT tp FROM TouristicPicture tp " +
                                                                       "LEFT JOIN FETCH tp.user u " +
                                                                       "WHERE u.id = :id", TouristicPicture.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<TouristicPicture> findTouristicPicturesByCity(String name){
        TypedQuery<TouristicPicture> query = entityManager.createQuery("SELECT pp.touristicPicture FROM PicturePlace pp " +
                                                                       "WHERE pp.city.city = :name", TouristicPicture.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<TouristicPicture> findTouristicPicturesByCommune(String name){
        TypedQuery<TouristicPicture> query = entityManager.createQuery("SELECT pp.touristicPicture FROM PicturePlace pp " +
                                                                       "WHERE pp.commune.commune = :name", TouristicPicture.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<TouristicPicture> findTouristicPicturesByVillage(String name){
        TypedQuery<TouristicPicture> query = entityManager.createQuery("SELECT pp.touristicPicture FROM PicturePlace pp " +
                                                                       "WHERE pp.village.village = :name", TouristicPicture.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<TouristicPicture> findTouristicPicturesByPlaceName(String name){
        TypedQuery<TouristicPicture> query = entityManager.createQuery("SELECT pp.touristicPicture FROM PicturePlace pp " +
                                                                       "WHERE pp.placeName.name = :name", TouristicPicture.class);

        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void persistNewPicture(TouristicPicture touristicPicture){
        entityManager.persist(touristicPicture);
    }

    @Override
    @Transactional
    public void removePicture(TouristicPicture touristicPicture){
        entityManager.remove(touristicPicture);
    }
    @Override
    public TouristicPicture findPictureByIdAndUserId(long userId, long pictureId){
        TypedQuery<TouristicPicture> query = entityManager.createQuery("SELECT tp FROM TouristicPicture tp " +
                "WHERE tp.id = :pictureId AND " +
                "tp.user.id = :userId", TouristicPicture.class);
        query.setParameter("pictureId", pictureId);
        query.setParameter("userId", userId);

        return query.getSingleResult();
    }
    @Override
    @Transactional
    public void removePicturePlace(PicturePlace picturePlace){
        entityManager.remove(picturePlace);
    }

    @Override
    @Transactional
    public void persistNewPictureComment(PictureComment pictureComment){
        entityManager.persist(pictureComment);
    }
    @Override
    public TouristicPicture findTouristicPictureById(long id){
        return entityManager.find(TouristicPicture.class, id);
    }

    @Override
    @Transactional
    public void mergePictureComment(PictureComment pictureComment){
        entityManager.merge(pictureComment);
    }

    @Override
    public List<PictureComment> findPictureComments(long id){
        TypedQuery<PictureComment> query = entityManager.createQuery("SELECT pc FROM PictureComment pc " +
                "WHERE pc.touristicPicture.id = :id", PictureComment.class);
        query.setParameter("id", id);

        return query.getResultList();
    }

    @Override
    public Long findPictureCommentsCount(long pictureId){
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(pc) FROM PictureComment pc " +
                "WHERE pc.touristicPicture.id = :id", Long.class);
        query.setParameter("id", pictureId);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void removePictureComment(PictureComment pictureComment){
        entityManager.remove(pictureComment);
    }
    @Override
    public PictureComment findPictureComment(long userId, long commentId){
        TypedQuery<PictureComment> query = entityManager.createQuery("SELECT pc FROM PictureComment pc " +
                "WHERE pc.user.id = :userId AND " +
                "pc.id = :commentId", PictureComment.class);
        query.setParameter("userId", userId);
        query.setParameter("commentId", commentId);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void persistNewPictureLike(PictureLike pictureLike){
        entityManager.persist(pictureLike);
    }

    @Override
    public List<PictureLike> findPictureLikes(long pictureId){
        TypedQuery<PictureLike> query = entityManager.createQuery("SELECT pl FROM PictureLike pl " +
                "WHERE pl.touristicPicture.id = :id", PictureLike.class);
        query.setParameter("id", pictureId);

        return query.getResultList();
    }

    @Override
    public Long findPictureLikesCount(long pictureId){
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(pl) FROM PictureLike pl " +
                "WHERE pl.touristicPicture.id = :id", Long.class);
        query.setParameter("id", pictureId);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void removePictureLike(PictureLike pictureLike){
        entityManager.remove(pictureLike);
    }
    @Override
    public PictureLike findPictureLike(long userId, long pictureId){
        TypedQuery<PictureLike> query = entityManager.createQuery("SELECT pl FROM PictureLike pl " +
                "WHERE pl.user.id = :userId AND " +
                "pl.touristicPicture.id = :pictureId", PictureLike.class);
        query.setParameter("userId", userId);
        query.setParameter("pictureId", pictureId);

        return query.getSingleResult();
    }

    @Override
    public List<TouristicPicture> findTouristicPicturesByUserId(long userId){
        TypedQuery<TouristicPicture> query = entityManager.createQuery("SELECT tp FROM TouristicPicture tp " +
                                                                       "WHERE tp.user.id = :userId", TouristicPicture.class);
        query.setParameter("userId", userId);

        return query.getResultList();
    }

}
