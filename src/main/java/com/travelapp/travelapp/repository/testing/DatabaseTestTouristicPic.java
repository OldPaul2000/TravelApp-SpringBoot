package com.travelapp.travelapp.repository.testing;

import com.travelapp.travelapp.model.locations.*;
import com.travelapp.travelapp.model.postedpictures.GpsCoords;
import com.travelapp.travelapp.model.postedpictures.PicturePlace;
import com.travelapp.travelapp.model.postedpictures.TouristicPicture;
import com.travelapp.travelapp.model.userrelated.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DatabaseTestTouristicPic {

    private AppDAO appDAO;

    @Autowired
    public DatabaseTestTouristicPic(AppDAO appDAO) {
        this.appDAO = appDAO;
    }


    //Unfinished
//    @Transactional
//    public void addCommentToPicture(int userId,TouristicPicture touristicPicture, String comment){
//        User user = (User)appDAO.findById(User.class, userId);
//        PictureComment picComment = new PictureComment(comment);
//    }

    public List<TouristicPicture> getTouristicPicturesByUser(int id){
        User user = appDAO.findUserById(id);
        return appDAO.getTouristicPicturesByUser(user);
    }
    public List<TouristicPicture> getTouristicPicturesForUser(int id){
        return appDAO.getTouristicPicturesByUserId(id);
    }

    @Transactional
    public void postNewPicture(){
        TouristicPicture touristicPicture = new TouristicPicture();
        touristicPicture.setCaptureDateTime(LocalDateTime.now());
        touristicPicture.setFileName("cascada-pisoaia-arieseni-(02).jpg");
        touristicPicture.setDescription("Este o cascada foarte frumoasa");

        GpsCoords gpsCoords = new GpsCoords(46.36156, 22.87749);
        touristicPicture.setCoordinates(gpsCoords);
        gpsCoords.setTouristicPicture(touristicPicture);

        User user = appDAO.findUserWithPicturesByFullName("Paul", "Batrin");
        user.addTouristicPicture(touristicPicture);
        touristicPicture.setUser(user);

        Country country = appDAO.getCountryByNameWithPicturePlaces("Romania");
        City city = appDAO.getCityByNameWithPicturePlaces("Alba Iulia");
        Commune commune = appDAO.getCommuneByNameWithPicturePlaces("Gârda de Sus");
        Village village = appDAO.getVillageByNameWithPicturePlaces("Dealu Frumos");
        PlaceName placeName = appDAO.getPlaceNameByNameWithPicturePlaces("Cascada Pisoaia");

        PicturePlace picturePlace = new PicturePlace();
        picturePlace.setTouristicPicture(touristicPicture);
        picturePlace.setCountry(country);
        picturePlace.setCity(city);
        picturePlace.setCommune(commune);
        picturePlace.setVillage(village);
        picturePlace.setPlaceName(placeName);
        touristicPicture.setPicturePlace(picturePlace);

        country.addPicturePlace(picturePlace);
        city.addPicturePlace(picturePlace);
        commune.addPicturePlace(picturePlace);
        village.addPicturePlace(picturePlace);
        placeName.setPicturePlace(picturePlace);

//        appDAO.addNewEntry(picturePlace);
//        appDAO.addNewEntry(touristicPicture);
    }
}
