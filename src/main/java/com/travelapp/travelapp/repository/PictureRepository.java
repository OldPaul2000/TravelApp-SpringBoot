package com.travelapp.travelapp.repository;

import com.travelapp.travelapp.model.postedpictures.TouristicPicture;

import java.util.List;

public interface PictureRepository {

    List<TouristicPicture> getTouristicPicturesByUser(int id);

    List<TouristicPicture> getTouristicPicturesByCity(String city);

    List<TouristicPicture> getTouristicPicturesByCommune(String commune);

    List<TouristicPicture> getTouristicPicturesByVillage(String village);

    List<TouristicPicture> getTouristicPicturesByPlaceName(String placeName);

    void postNewPicture(TouristicPicture touristicPicture);

}
