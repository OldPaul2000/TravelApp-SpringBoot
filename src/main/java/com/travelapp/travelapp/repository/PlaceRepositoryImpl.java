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
    public Country getCountryWithCities(String name){
        TypedQuery<Country> countryQuery = entityManager.createQuery("SELECT c FROM Country c " +
                                                                     "LEFT JOIN FETCH c.cities " +
                                                                     "WHERE c.country = :data", Country.class);
        countryQuery.setParameter("data", name);

        return countryQuery.getSingleResult();
    }

    @Override
    public Country getCountryByIdWithCities(int id){
        TypedQuery<Country> countryQuery = entityManager.createQuery("SELECT c FROM Country c " +
                                                                     "LEFT JOIN FETCH c.cities " +
                                                                     "WHERE c.id = :data", Country.class);
        countryQuery.setParameter("data", id);

        return countryQuery.getSingleResult();
    }

    @Override
    public City getCityWithCommunes(String name){
        TypedQuery<City> cityQuery = entityManager.createQuery("SELECT c FROM City c " +
                                                               "LEFT JOIN FETCH c.communes " +
                                                               "WHERE c.city = :data", City.class);
        cityQuery.setParameter("data", name);
        return cityQuery.getSingleResult();
    }

    @Override
    public Village getVillage(String name){
        TypedQuery<Village> villageQuery = entityManager.createQuery("SELECT v FROM Village v " +
                                                                     "WHERE v.village = :data", Village.class);
        villageQuery.setParameter("data", name);
        return villageQuery.getSingleResult();
    }

    @Override
    public City getCityByIdWithCommunes(int id){
        TypedQuery<City> cityQuery = entityManager.createQuery("SELECT c FROM City c " +
                                                               "LEFT JOIN FETCH c.communes " +
                                                               "WHERE c.id = :data", City.class);
        cityQuery.setParameter("data", id);
        return cityQuery.getSingleResult();
    }

    @Override
    public Commune getCommuneWithVillages(String name){
        TypedQuery<Commune> communeQuery = entityManager.createQuery("SELECT c FROM Commune c " +
                                                                     "LEFT JOIN FETCH c.villages " +
                                                                     "WHERE c.commune = :data", Commune.class);
        communeQuery.setParameter("data", name);
        return communeQuery.getSingleResult();
    }

    @Override
    public Commune getCommuneByIdWithVillages(int id){
        TypedQuery<Commune> communeQuery = entityManager.createQuery("SELECT c FROM Commune c " +
                                                                     "LEFT JOIN FETCH c.villages " +
                                                                     "WHERE c.id = :data", Commune.class);
        communeQuery.setParameter("data", id);
        return communeQuery.getSingleResult();
    }

    @Override
    public PlaceName getPlaceNameByName(String name){
        TypedQuery<PlaceName> placeNameQuery = entityManager.createQuery("SELECT pn FROM PlaceName pn " +
                                                                         "WHERE pn.name = :data", PlaceName.class);
        placeNameQuery.setParameter("data", name);
        return placeNameQuery.getSingleResult();
    }

    @Override
    @Transactional
    public void addNewCountry(Country country) {
        entityManager.persist(country);
    }

    @Override
    @Transactional
    public void addPlaceName(PlaceName placeName){
        entityManager.persist(placeName);
    }

    @Override
    @Transactional
    public void updateCountry(Country country) {
        entityManager.merge(country);
    }

    @Override
    @Transactional
    public void updateCity(City city) {
        entityManager.merge(city);
    }

    @Override
    @Transactional
    public void updateCommune(Commune commune) {
        entityManager.merge(commune);
    }

}
