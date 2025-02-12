package com.travelapp.travelapp.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public final class JWTConstants {

    private static String JWT_SECRET_KEY;
    private static String JWT_SECRET_DEFAULT_VALUE;
    private static String JWT_HEADER;

    public JWTConstants(@Value("${jwt.JWT_SECRET_KEY}") String SECRET_KEY,
                        @Value("${jwt.JWT_SECRET_DEFAULT_VALUE}") String SECRET_DEFAULT_VALUE,
                        @Value("${jwt.JWT_HEADER}") String HEADER) {
        JWT_SECRET_KEY = SECRET_KEY;
        JWT_SECRET_DEFAULT_VALUE = SECRET_DEFAULT_VALUE;
        JWT_HEADER = HEADER;
    }

    public static String getJwtSecretKey() {
        return JWT_SECRET_KEY;
    }

    public static String getJwtSecretDefaultValue() {
        return JWT_SECRET_DEFAULT_VALUE;
    }

    public static String getJwtHeader() {
        return JWT_HEADER;
    }
}
