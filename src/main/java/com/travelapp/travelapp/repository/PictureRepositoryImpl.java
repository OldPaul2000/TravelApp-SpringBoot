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
    public List<TouristicPicture> getTouristicPicturesByUser(int id) {
        TypedQuery<TouristicPicture> picturesQuery = entityManager.createQuery("SELECT tp FROM TouristicPicture tp " +
                                                                               "LEFT JOIN FETCH tp.user u " +
                                                                               "WHERE u.id = :data", TouristicPicture.class);
        picturesQuery.setParameter("data", id);
        return picturesQuery.getResultList();
    }

    @Override
    public List<TouristicPicture> getTouristicPicturesByCity(String city){
        TypedQuery<TouristicPicture> picturesQuery = entityManager.createQuery("SELECT pp.touristicPicture FROM PicturePlace pp " +
                                                                               "WHERE pp.city.city = :data", TouristicPicture.class);
        picturesQuery.setParameter("data", city);
        return picturesQuery.getResultList();
    }

    @Override
    public List<TouristicPicture> getTouristicPicturesByCommune(String commune){
        TypedQuery<TouristicPicture> picturesQuery = entityManager.createQuery("SELECT pp.touristicPicture FROM PicturePlace pp " +
                                                                               "WHERE pp.commune.commune = :data", TouristicPicture.class);
        picturesQuery.setParameter("data", commune);
        return picturesQuery.getResultList();
    }

    @Override
    public List<TouristicPicture> getTouristicPicturesByVillage(String village){
        TypedQuery<TouristicPicture> picturesQuery = entityManager.createQuery("SELECT pp.touristicPicture FROM PicturePlace pp " +
                                                                               "WHERE pp.village.village = :data", TouristicPicture.class);
        picturesQuery.setParameter("data", village);
        return picturesQuery.getResultList();
    }

    @Override
    public List<TouristicPicture> getTouristicPicturesByPlaceName(String placeName){
        TypedQuery<TouristicPicture> picturesQuery = entityManager.createQuery("SELECT pp.touristicPicture FROM PicturePlace pp " +
                                                                               "WHERE pp.placeName.name = :data", TouristicPicture.class);

        picturesQuery.setParameter("data", placeName);
        return picturesQuery.getResultList();
    }

    @Override
    public TouristicPicture getTouristicPictureById(int id){
        return entityManager.find(TouristicPicture.class, id);
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
    public void deletePicturePlace(PicturePlace picturePlace){
        entityManager.remove(picturePlace);
    }

    @Override
    @Transactional
    public void deletePictureCoordinates(GpsCoords coords){
        entityManager.remove(coords);
    }

    @Override
    @Transactional
    public void addPictureComment(PictureComment pictureComment){
        entityManager.persist(pictureComment);
    }

    @Override
    public List<PictureComment> getPictureComments(int id){
        TypedQuery<PictureComment> commentsQuery = entityManager.createQuery("SELECT pc FROM PictureComment pc " +
                                                                             "WHERE pc.touristicPicture.id = :data", PictureComment.class);
        commentsQuery.setParameter("data", id);

        return commentsQuery.getResultList();
    }

    @Override
    public PictureComment getPictureComment(int userId, int commentId){
        TypedQuery<PictureComment> commentQuery = entityManager.createQuery("SELECT pc FROM PictureComment pc " +
                                                                            "WHERE pc.user.id = :userId AND " +
                                                                            "pc.id = :commentId", PictureComment.class);
        commentQuery.setParameter("userId", userId);
        commentQuery.setParameter("commentId", commentId);

        return commentQuery.getSingleResult();
    }

    @Override
    public Long getPictureCommentsCount(int pictureId){
        TypedQuery<Long> commentsQuery = entityManager.createQuery("SELECT COUNT(pc) FROM PictureComment pc " +
                                                                   "WHERE pc.touristicPicture.id = :data", Long.class);
        commentsQuery.setParameter("data", pictureId);

        return commentsQuery.getSingleResult();
    }

    @Override
    @Transactional
    public void deletePictureComment(PictureComment pictureComment){
        entityManager.remove(pictureComment);
    }

    @Override
    @Transactional
    public void postNewPicture(TouristicPicture touristicPicture){
        entityManager.persist(touristicPicture);
    }

    @Override
    @Transactional
    public void updatePicture(TouristicPicture touristicPicture){
        entityManager.merge(touristicPicture);
    }

    @Override
    @Transactional
    public void deletePicture(TouristicPicture touristicPicture){
        entityManager.remove(touristicPicture);
    }

    @Override
    @Transactional
    public void addPictureLike(PictureLike pictureLike){
        entityManager.persist(pictureLike);
    }

    @Override
    @Transactional
    public void removePictureLike(PictureLike pictureLike){
        entityManager.remove(pictureLike);
    }

    @Override
    public PictureLike getPictureLike(int userId, int pictureId){
        TypedQuery<PictureLike> likeQuery = entityManager.createQuery("SELECT pl FROM PictureLike pl " +
                                                                      "WHERE pl.user.id = :userId AND " +
                                                                      "pl.touristicPicture.id = :pictureId", PictureLike.class);
        likeQuery.setParameter("userId", userId);
        likeQuery.setParameter("pictureId", pictureId);

        return likeQuery.getSingleResult();
    }

    @Override
    public List<PictureLike> getPictureLikes(int pictureId){
        TypedQuery<PictureLike> likeQuery = entityManager.createQuery("SELECT pl FROM PictureLike pl " +
                                                                      "WHERE pl.touristicPicture.id = :data", PictureLike.class);
        likeQuery.setParameter("data", pictureId);

        return likeQuery.getResultList();
    }

    @Override
    public Long getPictureLikesCount(int pictureId){
        TypedQuery<Long> likeQuery = entityManager.createQuery("SELECT COUNT(pl) FROM PictureLike pl " +
                                                               "WHERE pl.touristicPicture.id = :data", Long.class);
        likeQuery.setParameter("data", pictureId);

        return likeQuery.getSingleResult();
    }


}
