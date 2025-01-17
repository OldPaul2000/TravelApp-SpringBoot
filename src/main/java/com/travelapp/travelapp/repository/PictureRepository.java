package com.travelapp.travelapp.repository;

import com.travelapp.travelapp.model.postedpictures.*;

import java.util.List;

public interface PictureRepository {

    List<TouristicPicture> getTouristicPicturesByUser(int id);

    List<TouristicPicture> getTouristicPicturesByCity(String city);

    List<TouristicPicture> getTouristicPicturesByCommune(String commune);

    List<TouristicPicture> getTouristicPicturesByVillage(String village);

    List<TouristicPicture> getTouristicPicturesByPlaceName(String placeName);

    TouristicPicture getTouristicPictureById(int id);

    TouristicPicture findPictureByIdAndUserId(long userId, long pictureId);

    void deletePicturePlace(PicturePlace picturePlace);

    void deletePictureCoordinates(GpsCoords coords);

    void addPictureComment(PictureComment pictureComment);

    List<PictureComment> getPictureComments(int id);

    PictureComment getPictureComment(int userId, int pictureId);

    Long getPictureCommentsCount(int pictureId);

    void deletePictureComment(PictureComment pictureComment);

    void postNewPicture(TouristicPicture touristicPicture);

    void updatePicture(TouristicPicture touristicPicture);

    // Need to implement after implementing the collage posts database logic
    void deletePicture(TouristicPicture touristicPicture);

    void addPictureLike(PictureLike pictureLike);

    void removePictureLike(PictureLike pictureLike);

    PictureLike getPictureLike(int userId, int pictureId);

    List<PictureLike> getPictureLikes(int pictureId);

    Long getPictureLikesCount(int pictureId);
}
