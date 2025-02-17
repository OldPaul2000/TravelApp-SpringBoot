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

    public LogoutService(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String jwt = request.getHeader(JWTConstants.getJwtHeader());
        long userId = -1;
        if(jwt != null){
            try{
                String secret = JWTConstants.getJwtSecretKey();
                SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
                if(secretKey != null){
                    Claims claims = Jwts.parser().verifyWith(secretKey)
                            .build().parseSignedClaims(jwt).getPayload();
                    String username = String.valueOf(claims.get("username"));
                    String authorities = String.valueOf(claims.get("authorities"));
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
