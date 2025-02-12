package com.travelapp.travelapp.dto.collagepost;

import java.util.List;

public record CollageDTOPost(
        List<Integer> pictures,
        String description
) {
}
