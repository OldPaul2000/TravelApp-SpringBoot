package com.travelapp.travelapp.service;

import com.travelapp.travelapp.model.locations.*;
import com.travelapp.travelapp.model.postedpictures.PicturePlace;
import com.travelapp.travelapp.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PicturePlaceRemovalHelper {

    private PictureRepository pictureRepository;

    @Autowired
    public PicturePlaceRemovalHelper(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public void removePlaceFromPicture(PicturePlace picturePlace) {
        Country country = picturePlace.getCountry();
        City city = picturePlace.getCity();
        Commune commune = picturePlace.getCommune();
        Village village = picturePlace.getVillage();
        PlaceName placeName = picturePlace.getPlaceName();
        PlaceType placeType = picturePlace.getPlaceType();

        country.getPicturePlaces().remove(picturePlace);
        picturePlace.setCountry(null);
        city.getPicturePlaces().remove(picturePlace);
        picturePlace.setCity(null);
        commune.getPicturePlaces().remove(picturePlace);
        picturePlace.setCommune(null);
        village.getPicturePlaces().remove(picturePlace);
        picturePlace.setVillage(null);
        placeName.setPicturePlace(null);
        picturePlace.setPlaceName(null);
        placeType.removePicturePlace(picturePlace);
        picturePlace.setPlaceType(null);
        picturePlace.getTouristicPicture().setPicturePlace(null);
        picturePlace.setTouristicPicture(null);

        pictureRepository.removePicturePlace(picturePlace);
    }

}
