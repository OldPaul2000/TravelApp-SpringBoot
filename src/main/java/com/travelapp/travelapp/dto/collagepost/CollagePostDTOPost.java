package com.travelapp.travelapp.dto.collagepost;

import java.util.List;

public record CollagePostInfoDTO(
        Long userId,
        List<Integer> pictures,
        String description
) {
}
