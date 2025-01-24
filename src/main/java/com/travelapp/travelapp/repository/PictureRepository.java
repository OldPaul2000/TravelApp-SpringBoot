package com.travelapp.travelapp.repository;

import com.travelapp.travelapp.model.postedpictures.*;

import java.util.List;

public interface PictureRepository {

    List<TouristicPicture> findTouristicPicturesByUser(int id);

    List<TouristicPicture> findTouristicPicturesByCity(String name);

    List<TouristicPicture> findTouristicPicturesByCommune(String name);

    List<TouristicPicture> findTouristicPicturesByVillage(String name);

    List<TouristicPicture> findTouristicPicturesByPlaceName(String name);

    void persistNewPicture(TouristicPicture touristicPicture);

    void removePicture(TouristicPicture touristicPicture);
    TouristicPicture findPictureByIdAndUserId(long userId, long pictureId);
    void removePicturePlace(PicturePlace picturePlace);

    void persistNewPictureComment(PictureComment pictureComment);

    TouristicPicture findTouristicPictureById(int id);

    List<PictureComment> findPictureComments(int id);

    Long findPictureCommentsCount(int pictureId);

    void removePictureComment(PictureComment pictureComment);
    PictureComment findPictureComment(int userId, int pictureId);

    void persistNewPictureLike(PictureLike pictureLike);

    List<PictureLike> findPictureLikes(int pictureId);

    Long findPictureLikesCount(int pictureId);

    void removePictureLike(PictureLike pictureLike);
    PictureLike findPictureLike(int userId, int pictureId);

}
