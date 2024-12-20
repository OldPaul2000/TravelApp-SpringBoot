package com.travelapp.travelapp.repository.testing;

import com.travelapp.travelapp.model.MyEntity;
import com.travelapp.travelapp.model.locations.*;
import com.travelapp.travelapp.model.postedpictures.TouristicPicture;
import com.travelapp.travelapp.model.userrelated.Role;
import com.travelapp.travelapp.model.userrelated.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{

    private EntityManager entityManager;
    @Autowired
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    //=======================================================================================================
    // FOR POSTED PICTURES ENTITIES

    public List<TouristicPicture> getTouristicPicturesByUser(User user){
        TypedQuery<TouristicPicture> pictureQuery = entityManager.createQuery("SELECT tp FROM TouristicPicture tp " +
                                                                              "LEFT JOIN FETCH tp.user " +
                                                                              "WHERE tp.user = :data", TouristicPicture.class);

        pictureQuery.setParameter("data", user);
        return pictureQuery.getResultList();
    }
    public List<TouristicPicture> getTouristicPicturesByUserId(int id){
        TypedQuery<User> pictureQuery = entityManager.createQuery("SELECT u FROM User u " +
                                                                  "LEFT JOIN FETCH u.touristicPictures " +
                                                                  "WHERE u.id = :data", User.class);

        pictureQuery.setParameter("data", id);
        User user = pictureQuery.getSingleResult();
        return user.getTouristicPictures();
    }


    //=======================================================================================================
    // FOR LOCATIONS ENTITIES
    // Not finished. Need to think for the most optimal queries structure

    @Override
    public Country getCountryByName(String name){
        TypedQuery<Country> countryQuery = entityManager.createQuery("SELECT c FROM Country c " +
                                                                     "LEFT JOIN FETCH c.cities " +
                                                                     "WHERE c.country = :data", Country.class);
        countryQuery.setParameter("data",name);

        return countryQuery.getSingleResult();
    }

    @Override
    public Country getCountryByNameWithPicturePlaces(String name){
        TypedQuery<Country> countryQuery = entityManager.createQuery("SELECT c FROM Country c " +
                                                                     "LEFT JOIN FETCH c.picturePlaces " +
                                                                     "WHERE c.country = :data", Country.class);
        countryQuery.setParameter("data",name);

        return countryQuery.getSingleResult();
    }

    @Override
    public City getCityByName(String name) {
        TypedQuery<City> cityQuery = entityManager.createQuery("SELECT c FROM City c " +
                                                               "WHERE c.city = :data", City.class);
        cityQuery.setParameter("data",name);

        return cityQuery.getSingleResult();
    }

    @Override
    public City getCityByNameWithPicturePlaces(String name) {
        TypedQuery<City> cityQuery = entityManager.createQuery("SELECT c FROM City c " +
                                                               "LEFT JOIN FETCH c.picturePlaces " +
                                                               "WHERE c.city = :data", City.class);
        cityQuery.setParameter("data",name);

        return cityQuery.getSingleResult();
    }

    @Override
    public Commune getCommuneByName(String name) {
        TypedQuery<Commune> communeQuery = entityManager.createQuery("SELECT c FROM Commune c " +
                                                                     "WHERE c.commune = :data", Commune.class);
        communeQuery.setParameter("data",name);

        return communeQuery.getSingleResult();
    }

    @Override
    public Commune getCommuneByNameWithPicturePlaces(String name) {
        TypedQuery<Commune> communeQuery = entityManager.createQuery("SELECT c FROM Commune c " +
                                                                     "LEFT JOIN FETCH c.picturePlaces " +
                                                                     "WHERE c.commune = :data", Commune.class);
        communeQuery.setParameter("data",name);

        return communeQuery.getSingleResult();
    }

    @Override
    public Village getVillageByName(String name) {
        TypedQuery<Village> villageQuery = entityManager.createQuery("SELECT v FROM Village v " +
                                                                     "WHERE v.village = :data", Village.class);
        villageQuery.setParameter("data",name);

        return villageQuery.getSingleResult();
    }

    @Override
    public Village getVillageByNameWithPicturePlaces(String name) {
        TypedQuery<Village> villageQuery = entityManager.createQuery("SELECT v FROM Village v " +
                                                                     "LEFT JOIN FETCH v.picturePlaces " +
                                                                     "WHERE v.village = :data", Village.class);
        villageQuery.setParameter("data",name);

        return villageQuery.getSingleResult();
    }

    @Override
    public PlaceName getPlaceNameByName(String name) {
        TypedQuery<PlaceName> placeNameQuery = entityManager.createQuery("SELECT pn FROM PlaceName pn " +
                                                                         "WHERE pn.name = :data", PlaceName.class);
        placeNameQuery.setParameter("data",name);

        return placeNameQuery.getSingleResult();
    }

    @Override
    public PlaceName getPlaceNameByNameWithPicturePlaces(String name) {
        TypedQuery<PlaceName> placeNameQuery = entityManager.createQuery("SELECT pn FROM PlaceName pn " +
                                                                         "LEFT JOIN FETCH pn.picturePlace " +
                                                                         "WHERE pn.name = :data", PlaceName.class);
        placeNameQuery.setParameter("data",name);

        return placeNameQuery.getSingleResult();
    }


    //=======================================================================================================
    // FOR USER RELATED ENTITIES

    @Override
    public User getUserByIdWithPictureComments(int userId){
        TypedQuery<User> userQuery = entityManager.createQuery("SELECT u FROM User u " +
                                                               "JOIN FETCH u.pictureComments " +
                                                               "WHERE u.id = :data", User.class);
        userQuery.setParameter("data", userId);
        return userQuery.getSingleResult();
    }

    @Override
    public User findUserById(int id){
        TypedQuery<User> userQuery = entityManager.createQuery("SELECT u FROM User u " +
                                                               "JOIN FETCH u.roles " +
                                                               "JOIN FETCH u.userInfo " +
                                                               "WHERE u.id = :data", User.class);
        userQuery.setParameter("data", id);
        return userQuery.getSingleResult();
    }

    @Override
    public User findUserAndRolesById(int id){
        TypedQuery<User> userQuery = entityManager.createQuery("SELECT u FROM User u " +
                                                               "JOIN FETCH u.roles " +
                                                               "WHERE u.id = :data", User.class);
        userQuery.setParameter("data", id);
        return userQuery.getSingleResult();
    }

    @Override
    public Role findRoleById(int id){
        TypedQuery<Role> roleQuery = entityManager.createQuery("SELECT r FROM Role r " +
                                                               "JOIN FETCH r.user " +
                                                               "WHERE r.id = :data", Role.class);
        roleQuery.setParameter("data", id);
        return roleQuery.getSingleResult();
    }

    @Override
    public User findUserAndUserInfoById(int id){
        TypedQuery<User> userQuery = entityManager.createQuery("SELECT u FROM User u " +
                                                               "JOIN FETCH u.userInfo " +
                                                               "WHERE u.id = :data",User.class);
        userQuery.setParameter("data", id);
        return userQuery.getSingleResult();
    }

    @Override
    public User findUserByFullName(String firstName, String lastName){
        TypedQuery<User> userQuery = entityManager.createQuery("SELECT u FROM User u " +
                                                               "JOIN FETCH u.userInfo ui " +
                                                               "WHERE ui.firstName = :fName AND " +
                                                               "ui.lastName = :lName", User.class);
        userQuery.setParameter("fName", firstName);
        userQuery.setParameter("lName", lastName);

        return userQuery.getSingleResult();
    }

    @Override
    public User findUserWithPicturesByFullName(String firstName, String lastName){
        TypedQuery<User> userQuery = entityManager.createQuery("SELECT u FROM User u " +
                                                               "JOIN FETCH u.userInfo ui " +
                                                               "LEFT JOIN FETCH u.touristicPictures " +
                                                               "WHERE ui.firstName = :fName AND " +
                                                               "ui.lastName = :lName", User.class);
        userQuery.setParameter("fName", firstName);
        userQuery.setParameter("lName", lastName);

        return userQuery.getSingleResult();
    }

    //=======================================================================================================
    // FOR EVERY ENTITY
    public MyEntity findById(Class<? extends MyEntity> resultClass, int id){
        return entityManager.find(resultClass, id);
    }

    @Override
    @Transactional
    public void addNewEntry(MyEntity myEntity){
        entityManager.persist(myEntity);
    }

    @Override
    @Transactional
    public void updateEntry(MyEntity myEntity){
        entityManager.merge(myEntity);
    }

    @Override
    @Transactional
    public void deleteEntry(MyEntity myEntity){
        entityManager.remove(myEntity);
    }
}
