package com.travelapp.travelapp.dto.mappers;

import com.travelapp.travelapp.dto.places.*;
import com.travelapp.travelapp.model.postedpictures.PicturePlace;
import org.springframework.stereotype.Component;

@Component
public class PicturePlaceMapper {

    public PicturePlaceDTOGet toDTO(PicturePlace picturePlace){

        return new PicturePlaceDTOGet(
                picturePlace.getId(),
                new CountryDTOGet(
                        picturePlace.getCountry().getId(),
                        picturePlace.getCountry().getCountry(),
                        null
                ),
                new CityDTOGet(
                        picturePlace.getCity().getId(),
                        picturePlace.getCity().getCity(),
                        null
                ),
                new CommuneDTOGet(
                        picturePlace.getCommune().getId(),
                        picturePlace.getCommune().getCommune(),
                        null
                ),
                new VillageDTOGet(
                        picturePlace.getVillage().getId(),
                        picturePlace.getVillage().getVillage()
                ),
                new PlaceNameDTOGet(
                        picturePlace.getPlaceName().getId(),
                        picturePlace.getPlaceName().getName()
                )
        );

    }

}
