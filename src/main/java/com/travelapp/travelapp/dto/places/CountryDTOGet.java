package com.travelapp.travelapp.dto.places;

import java.util.List;

public record CountryDTOGet(
        Integer id,
        String country,
        List<CityDTOGet> cities
) {
}
