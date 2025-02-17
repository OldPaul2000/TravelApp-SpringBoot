package com.travelapp.travelapp.repository;

import com.travelapp.travelapp.model.security.JWT;

public interface JWTRepository {

    JWT findTokenByUserId(long userId);

    void persistToken(JWT jwt);

    void mergeToken(JWT jwt);

    void removeToken(JWT jwt);

}
