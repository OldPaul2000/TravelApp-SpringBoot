package com.travelapp.travelapp.repository;

import com.travelapp.travelapp.model.locations.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PlaceRepositoryImpl implements PlaceRepository {

    private EntityManager entityManager;

    public PlaceRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Country findCountryWithCities(String name){
        TypedQuery<Country> query = entityManager.createQuery("SELECT c FROM Country c " +
                                                              "LEFT JOIN FETCH c.cities " +
                                                              "WHERE c.country = :name", Country.class);
        query.setParameter("name", name);

        return query.getSingleResult();
    }

    @Override
    public City findCityWithCommunes(String name){
        TypedQuery<City> query = entityManager.createQuery("SELECT c FROM City c " +
                "LEFT JOIN FETCH c.communes " +
                "WHERE c.city = :name", City.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public Commune findCommuneWithVillages(String name){
        TypedQuery<Commune> query = entityManager.createQuery("SELECT c FROM Commune c " +
                "LEFT JOIN FETCH c.villages " +
                "WHERE c.commune = :name", Commune.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public Village findVillage(String name){
        TypedQuery<Village> query = entityManager.createQuery("SELECT v FROM Village v " +
                "WHERE v.village = :name", Village.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void persistNewCountry(Country country) {
        entityManager.persist(country);
    }

    @Override
    public Country findCountryByIdWithCities(int id){
        TypedQuery<Country> query = entityManager.createQuery("SELECT c FROM Country c " +
                "LEFT JOIN FETCH c.cities " +
                "WHERE c.id = :id", Country.class);
        query.setParameter("id", id);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void mergeCountry(Country country) {
        entityManager.merge(country);
    }

    @Override
    public City findCityByIdWithCommunes(int id){
        TypedQuery<City> query = entityManager.createQuery("SELECT c FROM City c " +
                "LEFT JOIN FETCH c.communes " +
                "WHERE c.id = :id", City.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void mergeCity(City city) {
        entityManager.merge(city);
    }

    @Override
    public Commune findCommuneByIdWithVillages(int id){
        TypedQuery<Commune> query = entityManager.createQuery("SELECT c FROM Commune c " +
                "LEFT JOIN FETCH c.villages " +
                "WHERE c.id = :id", Commune.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void mergeCommune(Commune commune) {
        entityManager.merge(commune);
    }




    @Override
    public PlaceName findPlaceNameByName(String name){
        TypedQuery<PlaceName> query = entityManager.createQuery("SELECT pn FROM PlaceName pn " +
                                                                         "WHERE pn.name = :name", PlaceName.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void persistNewPlaceName(PlaceName placeName){
        entityManager.persist(placeName);
    }

}
