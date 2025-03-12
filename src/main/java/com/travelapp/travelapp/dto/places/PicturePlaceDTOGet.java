package com.travelapp.travelapp.dto.places;

public record PicturePlaceDTOGet(
        Long id,
        CountryDTOGet country,
        CityDTOGet city,
        CommuneDTOGet commune,
        VillageDTOGet village,
        PlaceNameDTOGet placeName,
        PlaceTypeDTOGet placeType
) {
}
