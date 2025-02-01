package com.travelapp.travelapp.dto.userrelated;

public record UserDTORegister(
        String username,
        String password,
        String[] roles,
        UserInfoDTORegister userInfo
) {
}
