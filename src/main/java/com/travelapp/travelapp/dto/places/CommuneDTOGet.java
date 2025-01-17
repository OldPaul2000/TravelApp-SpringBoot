package com.travelapp.travelapp.dto.places;

import java.util.List;

public record CommuneDTOGet(
        Long id,
        String commune,
        List<VillageDTOGet> villages
) {
}
