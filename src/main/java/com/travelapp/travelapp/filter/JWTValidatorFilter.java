package com.travelapp.travelapp.filter;

import com.travelapp.travelapp.constants.JWTConstants;
import com.travelapp.travelapp.model.security.JWT;
import com.travelapp.travelapp.repository.JWTRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class JWTValidatorFilter extends OncePerRequestFilter {

    private JWTRepository jwtRepository;

    public JWTValidatorFilter(JWTRepository jwtRepository) {
        this.jwtRepository = jwtRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, ExpiredJwtException {
        String jwt = request.getHeader(JWTConstants.getJwtHeader());
        if(jwt != null){
            try {
                String secret = JWTConstants.getJwtSecretKey();
                SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
                if (secretKey != null) {
                    Claims claims = Jwts.parser().verifyWith(secretKey)
                            .build().parseSignedClaims(jwt).getPayload();
                    String username = String.valueOf(claims.get("username"));
                    String authorities = String.valueOf(claims.get("authorities"));
                    long userId = Long.parseLong(String.valueOf(claims.get("userId")));

                    currentTokenIsValid(userId, jwt);
                    Authentication authentication = new UsernamePasswordAuthenticationToken(username, null,
                            AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            catch (InvalidKeyException e){
                response.setHeader("cause", "JWT is invalid");
            }
            catch (Exception e){
                response.setHeader("cause", "JWT expired");
            }
        }
        filterChain.doFilter(request, response);
    }

//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        return request.getServletPath().equals("/api/v1/users/user");
//    }

    private void currentTokenIsValid(long userId, String token) throws InvalidKeyException{
        JWT currentToken = jwtRepository.findTokenByUserId(userId);
        if(currentToken == null || !currentToken.getToken().equals(token)){
            throw new InvalidKeyException("JWT is invalid");
        }
    }



}
