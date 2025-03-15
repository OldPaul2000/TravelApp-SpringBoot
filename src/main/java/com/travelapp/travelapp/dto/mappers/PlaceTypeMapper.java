package com.travelapp.travelapp.dto.mappers;

import com.travelapp.travelapp.dto.places.PlaceTypeDTOGet;
import com.travelapp.travelapp.model.locations.PlaceType;
import org.springframework.stereotype.Component;

@Component
public class PlaceTypeMapper {

    public PlaceTypeDTOGet toDTO(PlaceType placeType){
        PlaceTypeDTOGet placeTypeDTO = new PlaceTypeDTOGet(
                placeType.getId(),
                placeType.getPlaceType()
        );

        return placeTypeDTO;
    }

}
