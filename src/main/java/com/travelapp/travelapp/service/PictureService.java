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
import com.travelapp.travelapp.securityexceptionhandling.UserNotMatchingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

import static com.travelapp.travelapp.restcontroller.exceptionhandling.customerrormessage.PictureErrorMessages.ALREADY_LIKED_PICTURE;
import static com.travelapp.travelapp.restcontroller.exceptionhandling.customerrormessage.PictureErrorMessages.PICTURE_NOT_FOUND;
import static com.travelapp.travelapp.restcontroller.exceptionhandling.customerrormessage.UserErrorMessages.USER_NOT_FOUND;
import static com.travelapp.travelapp.securityexceptionhandling.SecurityErrorMessages.USER_NOT_MATCHING;

@Service
public class PictureService {

    private CurrentUserVerifier currentUserVerifier;

    private PictureRepository pictureRepository;
    private UserRepository userRepository;
    private PlaceRepository placeRepository;

    private TouristicPictureMapper pictureMapper;
    private PictureCommentMapper pictureCommentMapper;
    private PictureLikeMapper pictureLikeMapper;
    private FileStorageService fileStorageService;

    private PicturePlaceRemovalHelper picturePlaceRemovalHelper;

    @Autowired
    public PictureService(CurrentUserVerifier currentUserVerifier,
                          PictureRepository pictureRepository,
                          UserRepository userRepository,
                          PlaceRepository placeRepository,
                          TouristicPictureMapper pictureMapper,
                          PictureCommentMapper pictureCommentMapper,
                          PictureLikeMapper pictureLikeMapper,
                          FileStorageService fileStorageService,
                          PicturePlaceRemovalHelper picturePlaceRemovalHelper) {
        this.currentUserVerifier = currentUserVerifier;
        this.pictureRepository = pictureRepository;
        this.userRepository = userRepository;
        this.placeRepository = placeRepository;
        this.pictureMapper = pictureMapper;
        this.pictureCommentMapper = pictureCommentMapper;
        this.pictureLikeMapper = pictureLikeMapper;
        this.fileStorageService = fileStorageService;
        this.picturePlaceRemovalHelper = picturePlaceRemovalHelper;
    }

    /* Works */
    public List<TouristicPictureDTOGet> getTouristicPicturesByUser(long userId){
        List<TouristicPictureDTOGet> pictures = pictureRepository.findTouristicPicturesByUser(userId)
                .stream().map(picture -> pictureMapper.toDTO(picture))
                .toList();

        return pictures;
    }

    /* Works */
    public List<TouristicPictureDTOGet> getTouristicPicturesByCity(String cityName){
        List<TouristicPictureDTOGet> pictures = pictureRepository.findTouristicPicturesByCity(cityName)
                .stream().map(picture -> pictureMapper.toDTO(picture))
                .toList();

        return pictures;
    }

    /* Works */
    public List<TouristicPictureDTOGet> getTouristicPicturesByCommune(String communeName){
        List<TouristicPictureDTOGet> pictures = pictureRepository.findTouristicPicturesByCommune(communeName)
                .stream().map(picture -> pictureMapper.toDTO(picture))
                .toList();

        return pictures;
    }

    /* Works */
    public List<TouristicPictureDTOGet> getTouristicPicturesByVillage(String villageName){
        List<TouristicPictureDTOGet> pictures = pictureRepository.findTouristicPicturesByVillage(villageName)
                .stream().map(picture -> pictureMapper.toDTO(picture))
                .toList();

        return pictures;
    }

    /* Works */
    public List<TouristicPictureDTOGet> getTouristicPicturesByPlaceName(String placeName){
        List<TouristicPictureDTOGet> pictures = pictureRepository.findTouristicPicturesByPlaceName(placeName)
                .stream().map(picture -> pictureMapper.toDTO(picture))
                .toList();

        return pictures;
    }


    // Need to build proper implementation
    @Transactional
    public void postNewPicture(MultipartFile file, TouristicPictureDTOPost touristicPictureDTO){

        User user;
        try{
            user = userRepository.findUserByIdWithTouristicPictures(touristicPictureDTO.userId());
        }
        catch (EmptyResultDataAccessException e){
            throw new UserNotFoundException(USER_NOT_FOUND.message());
        }
        if(!currentUserVerifier.isCurrentUser(user.getUsername())){
            throw new UserNotMatchingException(USER_NOT_MATCHING.message());
        }

        Country country = placeRepository.findCountryWithCities(touristicPictureDTO.country());
        City city = country.getCities().stream()
                    .filter(c -> c.getCity()
                            .equals(touristicPictureDTO.city()))
                    .toList().get(0);
        Commune commune = placeRepository.findCommuneWithVillages(touristicPictureDTO.commune());
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
            placeName = placeRepository.findPlaceNameByName(touristicPictureDTO.placeName());
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

        pictureRepository.persistNewPicture(touristicPicture);
    }

    /* Works */
    public void deletePicture(long userId, long pictureId){
        TouristicPicture touristicPicture = pictureRepository.findPictureByIdAndUserId(userId, pictureId);

        if(!currentUserVerifier.isCurrentUser(touristicPicture.getUser().getUsername())){
            throw new UserNotMatchingException(USER_NOT_MATCHING.message());
        }

        touristicPicture.getUser().getTouristicPictures().remove(touristicPicture);
        touristicPicture.setUser(null);

        picturePlaceRemovalHelper.removePlaceFromPicture(touristicPicture.getPicturePlace());

        touristicPicture.getCollagePosts().forEach(collagePost -> {
            collagePost.getTouristicPictures().remove(touristicPicture);
        });
        touristicPicture.setCollagePosts(null);

        pictureRepository.removePicture(touristicPicture);
    }

    /* Works */
    public void postPictureComment(long userId, long pictureId, PictureCommentDTOPost userComment){

        User user = userRepository.findUserById(userId);
        if(user == null){
            throw new UserNotFoundException(USER_NOT_FOUND.message());
        }
        if(!currentUserVerifier.isCurrentUser(user.getUsername())){
            throw new UserNotMatchingException(USER_NOT_MATCHING.message());
        }

        try{
            TouristicPicture touristicPicture = pictureRepository.findTouristicPictureById(pictureId);

            PictureComment comment = new PictureComment(userComment.comment());
            comment.setDateTime(LocalDateTime.now());
            comment.setTouristicPicture(touristicPicture);
            comment.setUser(user);
            comment.setEdited(false);

            pictureRepository.persistNewPictureComment(comment);
        }
        catch (EmptyResultDataAccessException e){
            throw new TouristicPictureNotFoundException(PICTURE_NOT_FOUND.message());
        }
    }

    /* Works */
    public void editPictureComment(long userId, long commentId, PictureCommentDTOPost editedComment){
        PictureComment comment = pictureRepository.findPictureComment(userId, commentId);

        if(!currentUserVerifier.isCurrentUser(comment.getUser().getUsername())){
            throw new UserNotMatchingException(USER_NOT_MATCHING.message());
        }
        comment.setComment(editedComment.comment());
        comment.setEdited(true);

        pictureRepository.mergePictureComment(comment);
    }

    /* Works */
    public List<PictureCommentDTOGet> getPictureComments(long pictureId){
        List<PictureComment> pictureComments = pictureRepository.findPictureComments(pictureId);
        return pictureComments.stream()
                .map(comment -> pictureCommentMapper.toDTO(comment)).toList();
    }

    /* Works */
    public Long getPictureCommentsCount(long pictureId){
        return pictureRepository.findPictureCommentsCount(pictureId);
    }

    /* Works */
    public void deletePictureComment(long userId, long commentId){
        PictureComment comment = pictureRepository.findPictureComment(userId, commentId);

        if(!currentUserVerifier.isCurrentUser(comment.getUser().getUsername())){
            throw new UserNotMatchingException(USER_NOT_MATCHING.message());
        }

        comment.setUser(null);
        comment.setTouristicPicture(null);

        pictureRepository.removePictureComment(comment);
    }

    /* Works */
    public void likePicture(long userId, long pictureId){
        User user = userRepository.findUserById(userId);
        if(user == null){
            throw new UserNotFoundException(USER_NOT_FOUND.message());
        }
        if(!currentUserVerifier.isCurrentUser(user.getUsername())){
            throw new UserNotMatchingException(USER_NOT_MATCHING.message());
        }

        TouristicPicture touristicPicture = pictureRepository.findTouristicPictureById(pictureId);
        if(touristicPicture == null){
            throw new TouristicPictureNotFoundException(PICTURE_NOT_FOUND.message());
        }

        PictureLike pictureLike = new PictureLike();
        pictureLike.setTouristicPicture(touristicPicture);
        pictureLike.setUser(user);

        try{
            pictureRepository.persistNewPictureLike(pictureLike);
        }
        catch (DataIntegrityViolationException e){
            throw new PictureAlreadyLikedException(ALREADY_LIKED_PICTURE.message());
        }
    }

    /* Works */
    public List<PictureLikeDTOGet> getPictureLikes(long pictureId){
        return pictureRepository.findPictureLikes(pictureId)
                .stream()
                .map(like -> pictureLikeMapper.toDTO(like))
                .toList();
    }

    /* Works */
    public Long getPictureLikesCount(long pictureId){
        return pictureRepository.findPictureLikesCount(pictureId);
    }

    /* Works */
    public void dislikePicture(long userId, long pictureId){
        PictureLike pictureLike = pictureRepository.findPictureLike(userId, pictureId);
        if(!currentUserVerifier.isCurrentUser(pictureLike.getUser().getUsername())){
            throw new UserNotMatchingException(USER_NOT_MATCHING.message());
        }
        pictureLike.setUser(null);
        pictureLike.setTouristicPicture(null);

        pictureRepository.removePictureLike(pictureLike);
    }


}
