package com.travelapp.travelapp.config;

import com.travelapp.travelapp.constants.JWTConstants;
import com.travelapp.travelapp.service.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Service
public class LogoutService implements LogoutHandler {

    private JWTService jwtService;
    private JWTConstants jwtConstants;

    public LogoutService(JWTService jwtService, JWTConstants jwtConstants) {
        this.jwtService = jwtService;
        this.jwtConstants = jwtConstants;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String jwt = request.getHeader(jwtConstants.getHEADER());
        long userId = -1;
        if(jwt != null){
            try{
                String secret = jwtConstants.getSECRET_KEY();
                SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
                if(secretKey != null){
                    Claims claims = Jwts.parser().verifyWith(secretKey)
                            .build().parseSignedClaims(jwt).getPayload();
                    userId = Long.parseLong(String.valueOf(claims.get("userId")));

                    jwtService.invalidateToken(userId, jwt);

                    SecurityContextHolder.clearContext();
                }
            }
            catch (Exception e){
                response.setHeader("cause", "JWT expired");
                SecurityContextHolder.clearContext();
            }
        }
    }
}
