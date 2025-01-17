package com.travelapp.travelapp.service;

import com.travelapp.travelapp.dto.mappers.PictureCommentMapper;
import com.travelapp.travelapp.dto.mappers.PictureLikeMapper;
import com.travelapp.travelapp.dto.mappers.TouristicPictureMapper;
import com.travelapp.travelapp.dto.postedpictures.*;
import com.travelapp.travelapp.model.locations.*;
import com.travelapp.travelapp.model.postedpictures.*;
import com.travelapp.travelapp.model.userrelated.User;
import com.travelapp.travelapp.repository.PictureRepository;
import com.travelapp.travelapp.repository.PlaceRepository;
import com.travelapp.travelapp.repository.UserRepository;
import com.travelapp.travelapp.restcontroller.exceptionhandling.touristicpictures.PictureAlreadyLikedException;
import com.travelapp.travelapp.restcontroller.exceptionhandling.touristicpictures.TouristicPictureNotFoundException;
import com.travelapp.travelapp.restcontroller.exceptionhandling.users.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.travelapp.travelapp.restcontroller.exceptionhandling.customerrormessage.PictureErrorMessages.ALREADY_LIKED_PICTURE;
import static com.travelapp.travelapp.restcontroller.exceptionhandling.customerrormessage.PictureErrorMessages.PICTURE_NOT_FOUND;
import static com.travelapp.travelapp.restcontroller.exceptionhandling.customerrormessage.UserErrorMessages.USER_NOT_FOUND;

@Service
public class PictureService {

    private PictureRepository pictureRepository;
    private UserRepository userRepository;
    private PlaceRepository placeRepository;

    private TouristicPictureMapper pictureMapper;
    private PictureCommentMapper pictureCommentMapper;
    private PictureLikeMapper pictureLikeMapper;

    @Autowired
    public PictureService(PictureRepository pictureRepository,
                          UserRepository userRepository,
                          PlaceRepository placeRepository,
                          TouristicPictureMapper pictureMapper,
                          PictureCommentMapper pictureCommentMapper,
                          PictureLikeMapper pictureLikeMapper) {
        this.pictureRepository = pictureRepository;
        this.userRepository = userRepository;
        this.placeRepository = placeRepository;
        this.pictureMapper = pictureMapper;
        this.pictureCommentMapper = pictureCommentMapper;
        this.pictureLikeMapper = pictureLikeMapper;
    }

    /* Works */
    public List<TouristicPictureDTOGet> getTouristicPicturesByUser(int userId){
        List<TouristicPictureDTOGet> pictures = pictureRepository.getTouristicPicturesByUser(userId)
                .stream().map(picture -> pictureMapper.toDTO(picture))
                .toList();

        return pictures;
    }

    /* Works */
    public List<TouristicPictureDTOGet> getTouristicPicturesByCity(String cityName){
        List<TouristicPictureDTOGet> pictures = pictureRepository.getTouristicPicturesByCity(cityName)
                .stream().map(picture -> pictureMapper.toDTO(picture))
                .toList();

        return pictures;
    }

    /* Works */
    public List<TouristicPictureDTOGet> getTouristicPicturesByCommune(String communeName){
        List<TouristicPictureDTOGet> pictures = pictureRepository.getTouristicPicturesByCommune(communeName)
                .stream().map(picture -> pictureMapper.toDTO(picture))
                .toList();

        return pictures;
    }

    /* Works */
    public List<TouristicPictureDTOGet> getTouristicPicturesByVillage(String villageName){
        List<TouristicPictureDTOGet> pictures = pictureRepository.getTouristicPicturesByVillage(villageName)
                .stream().map(picture -> pictureMapper.toDTO(picture))
                .toList();

        return pictures;
    }

    /* Works */
    public List<TouristicPictureDTOGet> getTouristicPicturesByPlaceName(String placeName){
        List<TouristicPictureDTOGet> pictures = pictureRepository.getTouristicPicturesByPlaceName(placeName)
                .stream().map(picture -> pictureMapper.toDTO(picture))
                .toList();

        return pictures;
    }

    /* Works */
    @Transactional
    public void postNewPicture(int userId, TouristicPictureDTOPost touristicPictureDTO){

        User user;
        try{
            user = userRepository.findUserByIdWithTouristicPictures(userId);
        }
        catch (EmptyResultDataAccessException e){
            throw new UserNotFoundException(USER_NOT_FOUND.message());
        }

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


    // updating picture_place error: could not execute statement [Column 'file_id' cannot be null]
    @Transactional
    public void deletePicture(long userId, long pictureId){
        TouristicPicture touristicPicture = pictureRepository.findPictureByIdAndUserId(userId, pictureId);
        User user = touristicPicture.getUser();
        touristicPicture.setUser(null);
        user.getTouristicPictures().remove(touristicPicture);

        touristicPicture.getPictureLikes().forEach(pictureLike -> {
            pictureLike.setTouristicPicture(null);
        });
        touristicPicture.setLikes(null);
        touristicPicture.getPictureComments().forEach(pictureComment -> {
            pictureComment.setTouristicPicture(null);
        });
        touristicPicture.setPictureComments(null);

        touristicPicture.getCollagePosts().forEach(collagePost -> {
            collagePost.getTouristicPictures().remove(touristicPicture);
        });
        touristicPicture.setCollagePosts(null);

        touristicPicture.getPicturePlace().setTouristicPicture(null);
        touristicPicture.setPicturePlace(null);

        touristicPicture.getCoordinates().setTouristicPicture(null);
        touristicPicture.setCoordinates(null);

        pictureRepository.deletePicture(touristicPicture);
    }

    /* Works */
    public void postPictureComment(int userId, int pictureId, PictureCommentDTOPost userComment){

        User user = userRepository.findUserById(userId);
        if(user == null){
            throw new UserNotFoundException(USER_NOT_FOUND.message());
        }

        try{
            TouristicPicture touristicPicture = pictureRepository.getTouristicPictureById(pictureId);

            PictureComment comment = new PictureComment(userComment.userComment());
            comment.setDateTime(LocalDateTime.now());
            comment.setTouristicPicture(touristicPicture);
            comment.setUser(user);

            pictureRepository.addPictureComment(comment);
        }
        catch (EmptyResultDataAccessException e){
            throw new TouristicPictureNotFoundException(PICTURE_NOT_FOUND.message());
        }
    }

    /* Works */
    public void deletePictureComment(int userId, int pictureId){
        PictureComment comment = pictureRepository.getPictureComment(userId, pictureId);

        comment.setUser(null);
        comment.getTouristicPicture()
                .getPictureComments()
                .remove(comment);
        comment.setTouristicPicture(null);

        pictureRepository.deletePictureComment(comment);
    }

    /* Works */
    public List<PictureCommentDTOGet> getPictureComments(int pictureId){
        List<PictureComment> pictureComments = pictureRepository.getPictureComments(pictureId);
        return pictureComments.stream()
                .map(comment -> pictureCommentMapper.toDTO(comment)).toList();
    }

    /* Works */
    public Long getPictureCommentsCount(int pictureId){
        return pictureRepository.getPictureCommentsCount(pictureId);
    }

    /* Works */
    public void likePicture(int userId, int pictureId){
        User user = userRepository.findUserById(userId);
        if(user == null){
            throw new UserNotFoundException(USER_NOT_FOUND.message());
        }

        TouristicPicture touristicPicture = pictureRepository.getTouristicPictureById(pictureId);
        if(touristicPicture == null){
            throw new TouristicPictureNotFoundException(PICTURE_NOT_FOUND.message());
        }

        PictureLike pictureLike = new PictureLike();
        pictureLike.setTouristicPicture(touristicPicture);
        pictureLike.setUser(user);

        try{
            pictureRepository.addPictureLike(pictureLike);
        }
        catch (DataIntegrityViolationException e){
            throw new PictureAlreadyLikedException(ALREADY_LIKED_PICTURE.message());
        }
    }

    /* Works */
    public void dislikePicture(int userId, int pictureId){
        PictureLike pictureLike = pictureRepository.getPictureLike(userId, pictureId);
        pictureLike.setUser(null);
        pictureLike.getTouristicPicture()
                .getPictureLikes()
                .remove(pictureLike);
        pictureLike.setTouristicPicture(null);

        pictureRepository.removePictureLike(pictureLike);
    }

    /* Works */
    public List<PictureLikeDTOGet> getPictureLikes(int pictureId){
        return pictureRepository.getPictureLikes(pictureId)
                .stream()
                .map(like -> pictureLikeMapper.toDTO(like))
                .toList();
    }

    /* Works */
    public Long getPictureLikesCount(int pictureId){
        return pictureRepository.getPictureLikesCount(pictureId);
    }

}
