package com.travelapp.travelapp.service;

import com.travelapp.travelapp.dto.mappers.*;
import com.travelapp.travelapp.dto.postedpictures.*;
import com.travelapp.travelapp.model.locations.*;
import com.travelapp.travelapp.model.postedpictures.*;
import com.travelapp.travelapp.model.userrelated.User;
import com.travelapp.travelapp.repository.PictureRepository;
import com.travelapp.travelapp.repository.PlaceRepository;
import com.travelapp.travelapp.repository.UserRepository;
import com.travelapp.travelapp.restcontroller.exceptionhandling.touristicpictures.FileAlreadyExistsException;
import com.travelapp.travelapp.restcontroller.exceptionhandling.touristicpictures.PictureAlreadyLikedException;
import com.travelapp.travelapp.restcontroller.exceptionhandling.touristicpictures.TouristicPictureNotFoundException;
import com.travelapp.travelapp.restcontroller.exceptionhandling.users.UserNotFoundException;
import com.travelapp.travelapp.securityexceptionhandling.NotEnoughPrivilegesException;
import com.travelapp.travelapp.securityexceptionhandling.UserNotMatchingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

import static com.travelapp.travelapp.restcontroller.exceptionhandling.customerrormessage.PictureErrorMessages.*;
import static com.travelapp.travelapp.restcontroller.exceptionhandling.customerrormessage.UserErrorMessages.USER_NOT_FOUND;
import static com.travelapp.travelapp.securityexceptionhandling.SecurityErrorMessages.NOT_ENOUGH_PRIVILEGES;
import static com.travelapp.travelapp.securityexceptionhandling.SecurityErrorMessages.USER_NOT_MATCHING;

@Service
public class PictureService {

    private UserPrivilegesVerifier userPrivilegesVerifier;

    private PictureRepository pictureRepository;
    private UserRepository userRepository;
    private PlaceRepository placeRepository;

    private TouristicPictureMapper pictureMapper;
    private PictureCommentMapper pictureCommentMapper;
    private PictureLikeMapper pictureLikeMapper;
    private PicturePlaceMapper picturePlaceMapper;
    private PostingUserMapper postingUserMapper;
    private FileStorageService fileStorageService;

    private PicturePlaceRemovalHelper picturePlaceRemovalHelper;

    @Autowired
    public PictureService(UserPrivilegesVerifier userPrivilegesVerifier,
                          PictureRepository pictureRepository,
                          UserRepository userRepository,
                          PlaceRepository placeRepository,
                          TouristicPictureMapper pictureMapper,
                          PictureCommentMapper pictureCommentMapper,
                          PictureLikeMapper pictureLikeMapper,
                          PicturePlaceMapper picturePlaceMapper,
                          PostingUserMapper postingUserMapper,
                          FileStorageService fileStorageService,
                          PicturePlaceRemovalHelper picturePlaceRemovalHelper) {
        this.userPrivilegesVerifier = userPrivilegesVerifier;
        this.pictureRepository = pictureRepository;
        this.userRepository = userRepository;
        this.placeRepository = placeRepository;
        this.pictureMapper = pictureMapper;
        this.pictureCommentMapper = pictureCommentMapper;
        this.pictureLikeMapper = pictureLikeMapper;
        this.picturePlaceMapper = picturePlaceMapper;
        this.postingUserMapper = postingUserMapper;
        this.fileStorageService = fileStorageService;
        this.picturePlaceRemovalHelper = picturePlaceRemovalHelper;
    }

    @Value("${app.files.touristic-pictures}")
    private String TOURISTIC_PICTURES_LOCATION;

    public TouristicPictureDTOGet getTouristicPictureById(long id){
        TouristicPicture picture = pictureRepository.findTouristicPictureById(id);
        byte[] fileBytes = fileStorageService
                .getFileBytes(picture.getUser().getId(),
                              TOURISTIC_PICTURES_LOCATION,
                              picture.getFileName());
        return pictureMapper.toDTO(picture, fileBytes);
    }

    public List<TouristicPictureDTOGet> getTouristicPicturesByUser(long userId){
        List<TouristicPicture> pictures = pictureRepository.findTouristicPicturesByUser(userId);
        return mapPicturesToDTO(pictures);
    }

    public List<TouristicPictureDTOGet> getTouristicPicturesByCity(String cityName){
        List<TouristicPicture> pictures = pictureRepository.findTouristicPicturesByCity(cityName);
        return mapPicturesToDTO(pictures);
    }

    public List<TouristicPictureDTOGet> getTouristicPicturesByCommune(String communeName){
        List<TouristicPicture> pictures = pictureRepository.findTouristicPicturesByCommune(communeName);
        return mapPicturesToDTO(pictures);
    }

    public List<TouristicPictureDTOGet> getTouristicPicturesByVillage(String villageName){
        List<TouristicPicture> pictures = pictureRepository.findTouristicPicturesByVillage(villageName);
        return mapPicturesToDTO(pictures);
    }

    public List<TouristicPictureDTOGet> getTouristicPicturesByPlaceName(String placeName){
        List<TouristicPicture> pictures = pictureRepository.findTouristicPicturesByPlaceName(placeName);
        return mapPicturesToDTO(pictures);
    }

    private List<TouristicPictureDTOGet> mapPicturesToDTO(List<TouristicPicture> pictures){
        return pictures.stream().map(picture -> {
            byte[] fileBytes = fileStorageService
                    .getFileBytes(picture.getUser().getId(),
                            TOURISTIC_PICTURES_LOCATION,
                            picture.getFileName());
            return pictureMapper.toDTO(picture, fileBytes);
        }).toList();
    }

    @Transactional
    public void postNewPicture(long userId, TouristicPictureDTOPost touristicPictureDTO, MultipartFile file) {

        User user;
        try{
            user = userRepository.findUserByIdWithTouristicPictures(userId);
        }
        catch (EmptyResultDataAccessException e){
            throw new UserNotFoundException(USER_NOT_FOUND.message());
        }
        if(!userPrivilegesVerifier.isCurrentUser(user.getUsername())){
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
        touristicPicture.setFileName(file.getOriginalFilename());
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

        try{
            pictureRepository.persistNewPicture(touristicPicture);
            fileStorageService.storeFile(userId, TOURISTIC_PICTURES_LOCATION, file.getOriginalFilename(), file.getBytes());
        }
        catch (Exception e) {
            throw new FileAlreadyExistsException(PICTURE_ALREADY_EXISTS.message());
        }
    }

    public void deletePicture(long userId, long pictureId){
        TouristicPicture touristicPicture = pictureRepository.findPictureByIdAndUserId(userId, pictureId);

        if(!userPrivilegesVerifier.isCurrentUser(touristicPicture.getUser().getUsername()) &&
           !userPrivilegesVerifier.hasEnoughPrivileges()) {
            throw new NotEnoughPrivilegesException(NOT_ENOUGH_PRIVILEGES.message());
        }

        touristicPicture.getUser().getTouristicPictures().remove(touristicPicture);
        touristicPicture.setUser(null);

        picturePlaceRemovalHelper.removePlaceFromPicture(touristicPicture.getPicturePlace());

        touristicPicture.getCollagePosts().forEach(collagePost -> {
            collagePost.getTouristicPictures().remove(touristicPicture);
        });
        touristicPicture.setCollagePosts(null);

        pictureRepository.removePicture(touristicPicture);
        fileStorageService.deleteFile(userId,
                                      TOURISTIC_PICTURES_LOCATION,
                                      touristicPicture.getFileName());
    }

    public void postPictureComment(long userId, long pictureId, PictureCommentDTOPost userComment){

        User user = userRepository.findUserById(userId);
        if(user == null){
            throw new UserNotFoundException(USER_NOT_FOUND.message());
        }
        if(!userPrivilegesVerifier.isCurrentUser(user.getUsername())){
            throw new UserNotMatchingException(USER_NOT_MATCHING.message());
        }

        TouristicPicture touristicPicture = pictureRepository.findTouristicPictureById(pictureId);
        if(touristicPicture == null){
            throw new TouristicPictureNotFoundException(PICTURE_NOT_FOUND.message());
        }

        PictureComment comment = new PictureComment(userComment.comment());
        comment.setDateTime(LocalDateTime.now());
        comment.setTouristicPicture(touristicPicture);
        comment.setUser(user);
        comment.setEdited(false);

        pictureRepository.persistNewPictureComment(comment);
    }

    public void editPictureComment(long userId, long commentId, PictureCommentDTOPost editedComment){
        PictureComment comment = pictureRepository.findPictureComment(userId, commentId);

        if(!userPrivilegesVerifier.isCurrentUser(comment.getUser().getUsername())){
            throw new UserNotMatchingException(USER_NOT_MATCHING.message());
        }
        comment.setComment(editedComment.comment());
        comment.setEdited(true);

        pictureRepository.mergePictureComment(comment);
    }

    public List<PictureCommentDTOGet> getPictureComments(long pictureId){
        List<PictureComment> pictureComments = pictureRepository.findPictureComments(pictureId);
        return pictureComments.stream()
                .map(comment -> pictureCommentMapper.toDTO(comment)).toList();
    }

    public Long getPictureCommentsCount(long pictureId){
        return pictureRepository.findPictureCommentsCount(pictureId);
    }

    public void deletePictureComment(long userId, long commentId){
        PictureComment comment = pictureRepository.findPictureComment(userId, commentId);

        if(!userPrivilegesVerifier.isCurrentUser(comment.getUser().getUsername()) &&
                !userPrivilegesVerifier.hasEnoughPrivileges()) {
            throw new NotEnoughPrivilegesException(NOT_ENOUGH_PRIVILEGES.message());
        }

        comment.setUser(null);
        comment.setTouristicPicture(null);

        pictureRepository.removePictureComment(comment);
    }

    public void likePicture(long userId, long pictureId){
        User user = userRepository.findUserById(userId);
        if(user == null){
            throw new UserNotFoundException(USER_NOT_FOUND.message());
        }
        if(!userPrivilegesVerifier.isCurrentUser(user.getUsername())){
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

    public List<PictureLikeDTOGet> getPictureLikes(long pictureId){
        return pictureRepository.findPictureLikes(pictureId)
                .stream()
                .map(like -> pictureLikeMapper.toDTO(like))
                .toList();
    }

    public Long getPictureLikesCount(long pictureId){
        return pictureRepository.findPictureLikesCount(pictureId);
    }

    public void dislikePicture(long userId, long pictureId){
        PictureLike pictureLike = pictureRepository.findPictureLike(userId, pictureId);
        if(!userPrivilegesVerifier.isCurrentUser(pictureLike.getUser().getUsername())){
            throw new UserNotMatchingException(USER_NOT_MATCHING.message());
        }
        pictureLike.setUser(null);
        pictureLike.setTouristicPicture(null);

        pictureRepository.removePictureLike(pictureLike);
    }


}
