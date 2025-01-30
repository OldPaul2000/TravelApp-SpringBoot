package com.travelapp.travelapp.dto.places;

import java.util.List;

public record CityDTOGet(
        Integer id,
        String city,
        List<CommuneDTOGet> communes
) {
}
