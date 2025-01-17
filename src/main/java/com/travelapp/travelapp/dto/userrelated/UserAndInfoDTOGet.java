package com.travelapp.travelapp.dto.userrelated;

import com.travelapp.travelapp.model.userrelated.Role;

import java.util.List;

public record UserAndInfoDTOGet(
        Long id,
        List<Role> roles,
        UserInfoDTOGet userInfo
) {
}
