package com.travelapp.travelapp.service;

import com.travelapp.travelapp.dto.postedpictures.TouristicPictureDTOPost;
import com.travelapp.travelapp.model.locations.*;
import com.travelapp.travelapp.model.postedpictures.GpsCoords;
import com.travelapp.travelapp.model.postedpictures.PicturePlace;
import com.travelapp.travelapp.model.postedpictures.TouristicPicture;
import com.travelapp.travelapp.model.userrelated.User;
import com.travelapp.travelapp.repository.PictureRepository;
import com.travelapp.travelapp.repository.PlaceRepository;
import com.travelapp.travelapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PictureService {

    private PictureRepository pictureRepository;
    private UserRepository userRepository;
    private PlaceRepository placeRepository;

    @Autowired
    public PictureService(PictureRepository pictureRepository, UserRepository userRepository, PlaceRepository placeRepository) {
        this.pictureRepository = pictureRepository;
        this.userRepository = userRepository;
        this.placeRepository = placeRepository;
    }

    public List<TouristicPicture> getTouristicPicturesByUser(int userId){
        return pictureRepository.getTouristicPicturesByUser(userId);
    }

    public List<TouristicPicture> getTouristicPicturesByCity(String cityName){
        return pictureRepository.getTouristicPicturesByCity(cityName);
    }

    public List<TouristicPicture> getTouristicPicturesByCommune(String communeName){
        return pictureRepository.getTouristicPicturesByCommune(communeName);
    }

    public List<TouristicPicture> getTouristicPicturesByVillage(String villageName){
        return pictureRepository.getTouristicPicturesByVillage(villageName);
    }

    public List<TouristicPicture> getTouristicPicturesByPlaceName(String placeName){
        return pictureRepository.getTouristicPicturesByPlaceName(placeName);
    }

    @Transactional
    public void postNewPicture(int userId, TouristicPictureDTOPost touristicPictureDTO){
        User user = userRepository.findUserByIdWithTouristicPictures(userId);

        Country country = placeRepository.getCountryWithCities(touristicPictureDTO.country());
        City city = country.getCities().stream()
                    .filter(c -> c.getCity()
                            .equals(touristicPictureDTO.city()))
                    .toList().get(0);
        Commune commune = placeRepository.getCommuneWithVillages(touristicPictureDTO.commune());
        Village village = commune.getVillages().stream()
                          .filter(v -> v.getVillage()
                                  .equals(touristicPictureDTO.village()))
                          .toList().get(0);

        TouristicPicture touristicPicture = new TouristicPicture();
        touristicPicture.setFileName(touristicPictureDTO.fileName());
        touristicPicture.setDescription(touristicPictureDTO.description());
        touristicPicture.setCaptureDateTime(LocalDateTime.now());
        touristicPicture.setUser(user);

        PicturePlace picturePlace = new PicturePlace();
        picturePlace.setCountry(country);
        picturePlace.setCity(city);
        picturePlace.setCommune(commune);
        picturePlace.setVillage(village);

        PlaceName placeName = null;
        try{
            placeName = placeRepository.getPlaceNameByName(touristicPictureDTO.placeName());
        }
        catch (EmptyResultDataAccessException e){
            placeName = new PlaceName(touristicPictureDTO.placeName());
        }
        finally {
            picturePlace.setPlaceName(placeName);
            placeName.setPicturePlace(picturePlace);
        }

        picturePlace.setPlaceName(placeName);
        placeName.setPicturePlace(picturePlace);

        GpsCoords gpsCoords = new GpsCoords();
        gpsCoords.setLatitude(touristicPictureDTO.latitude());
        gpsCoords.setLongitude(touristicPictureDTO.longitude());

        touristicPicture.setPicturePlace(picturePlace);
        picturePlace.setTouristicPicture(touristicPicture);

        touristicPicture.setCoordinates(gpsCoords);
        gpsCoords.setTouristicPicture(touristicPicture);

        pictureRepository.postNewPicture(touristicPicture);
    }

}
