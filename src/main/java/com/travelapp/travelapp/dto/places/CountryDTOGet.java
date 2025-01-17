package com.travelapp.travelapp.dto.places;

import java.util.List;

public record CountryDTOGet(
        Long id,
        String country,
        List<CityDTOGet> cities
) {
}
