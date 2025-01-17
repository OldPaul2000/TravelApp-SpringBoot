package com.travelapp.travelapp.dto.collagepost;

import java.util.List;

public record CollagePostDTOPost(
        int userId,
        List<Integer> pictures,
        String description
) {
}
