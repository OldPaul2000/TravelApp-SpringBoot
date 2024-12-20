package com.travelapp.travelapp.repository;

import com.travelapp.travelapp.model.postedpictures.TouristicPicture;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

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
    public void postNewPicture(TouristicPicture touristicPicture){
        entityManager.persist(touristicPicture);
    }


}
