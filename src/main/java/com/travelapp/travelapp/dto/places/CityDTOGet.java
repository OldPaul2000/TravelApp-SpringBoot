package com.travelapp.travelapp.dto.places;

import java.util.List;

public record CityDTOGet(
        Long id,
        String city,
        List<CommuneDTOGet> communes
) {
}
