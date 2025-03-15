package com.travelapp.travelapp.repository;

import com.travelapp.travelapp.model.postedpictures.PictureComment;
import com.travelapp.travelapp.model.postedpictures.PictureLike;
import com.travelapp.travelapp.model.postedpictures.PicturePlace;
import com.travelapp.travelapp.model.postedpictures.TouristicPicture;

import java.util.List;

public interface PictureRepository {


    List<TouristicPicture> findTouristicPicturesByUser(long id, int pageStart, int offset);

    List<TouristicPicture> findTouristicPicturesByUserAndPlaceType(long userId, String placeType, int pageStart, int offset);

    List<TouristicPicture> findTouristicPicturesByCity(String name, int pageStart, int offset);

    List<TouristicPicture> findTouristicPicturesByCityAndPlaceType(String city, String placeType, int pageStart, int offset);

    List<TouristicPicture> findTouristicPicturesByCommune(String name, int pageStart, int offset);

    List<TouristicPicture> findTouristicPicturesByCommuneAndPlaceType(String commune, String placeType, int pageStart, int offset);

    List<TouristicPicture> findTouristicPicturesByVillage(String name, int pageStart, int offset);

    List<TouristicPicture> findTouristicPicturesByVillageAndPlaceType(String village, String placeType, int pageStart, int offset);

    List<TouristicPicture> findTouristicPicturesByPlaceName(String name, int pageStart, int offset);

    void persistNewPicture(TouristicPicture touristicPicture);

    void removePicture(TouristicPicture touristicPicture);
    TouristicPicture findPictureByIdAndUserId(long userId, long pictureId);
    void removePicturePlace(PicturePlace picturePlace);

    void persistNewPictureComment(PictureComment pictureComment);
    TouristicPicture findTouristicPictureById(long id);

    void mergePictureComment(PictureComment pictureComment);

    List<PictureComment> findPictureComments(long id, int pageStart, int offset);

    Long findPictureCommentsCount(long pictureId);

    void removePictureComment(PictureComment pictureComment);
    PictureComment findPictureComment(long userId, long pictureId);

    void persistNewPictureLike(PictureLike pictureLike);

    List<PictureLike> findPictureLikes(long pictureId, int pageStart, int offset);

    Long findPictureLikesCount(long pictureId);

    void removePictureLike(PictureLike pictureLike);
    PictureLike findPictureLike(long userId, long pictureId);

    List<TouristicPicture> findTouristicPicturesByUserId(long userId);

}
