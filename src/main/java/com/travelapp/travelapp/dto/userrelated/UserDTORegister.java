package com.travelapp.travelapp.dto.userrelated;

public record UserDTORegister(
        String username,
        String password,
        UserInfoDTORegister userInfo
) {
}
