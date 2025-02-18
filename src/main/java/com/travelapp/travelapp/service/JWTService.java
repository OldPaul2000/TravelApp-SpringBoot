package com.travelapp.travelapp.service;

import com.travelapp.travelapp.model.security.JWT;
import com.travelapp.travelapp.model.userrelated.User;
import com.travelapp.travelapp.repository.JWTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JWTService {

    private JWTRepository jwtRepository;

    @Autowired
    public JWTService(JWTRepository jwtRepository) {
        this.jwtRepository = jwtRepository;
    }

    // Adds new token if there is none(the user is not logged in) or overwrites the existing one.
    // (We don't want to have 2 valid tokens. This will prevent the account to be
    // logged in on 2 different devices at the same time.
    // The user will be logged out from the previous device when attempting to log in
    // on another one)
    public void addNewToken(User user, String token){
        JWT currentJWT = jwtRepository.findTokenByUserId(user.getId());
        if(currentJWT != null){
            currentJWT.setToken(token);
            jwtRepository.mergeToken(currentJWT);
        }
        else{
            JWT newToken = new JWT(token);
            user.setJwt(newToken);
            user.getJwt().setUser(user);

            jwtRepository.persistToken(newToken);
        }
    }

    @Transactional
    public void invalidateToken(long userId, String currentToken){
        JWT token = jwtRepository.findTokenByUserId(userId);
        if(token.getToken().equals(currentToken)){
            token.getUser().setJwt(null);
            token.setUser(null);

            jwtRepository.removeToken(token);
        }
    }



}
