package com.travelapp.travelapp.repository;

import com.travelapp.travelapp.model.postedpictures.PictureComment;
import com.travelapp.travelapp.model.postedpictures.PictureLike;
import com.travelapp.travelapp.model.postedpictures.PicturePlace;
import com.travelapp.travelapp.model.postedpictures.TouristicPicture;

import java.util.List;

public interface PictureRepository {

    List<TouristicPicture> findTouristicPicturesByUser(long id);

    List<TouristicPicture> findTouristicPicturesByCity(String name);

    List<TouristicPicture> findTouristicPicturesByCommune(String name);

    List<TouristicPicture> findTouristicPicturesByVillage(String name);

    List<TouristicPicture> findTouristicPicturesByPlaceName(String name);

    void persistNewPicture(TouristicPicture touristicPicture);

    void removePicture(TouristicPicture touristicPicture);
    TouristicPicture findPictureByIdAndUserId(long userId, long pictureId);
    void removePicturePlace(PicturePlace picturePlace);

    void persistNewPictureComment(PictureComment pictureComment);
    TouristicPicture findTouristicPictureById(long id);

    void mergePictureComment(PictureComment pictureComment);

    List<PictureComment> findPictureComments(long id);

    Long findPictureCommentsCount(long pictureId);

    void removePictureComment(PictureComment pictureComment);
    PictureComment findPictureComment(long userId, long pictureId);

    void persistNewPictureLike(PictureLike pictureLike);

    List<PictureLike> findPictureLikes(long pictureId);

    Long findPictureLikesCount(long pictureId);

    void removePictureLike(PictureLike pictureLike);
    PictureLike findPictureLike(long userId, long pictureId);

    List<TouristicPicture> findTouristicPicturesByUserId(long userId);

}
