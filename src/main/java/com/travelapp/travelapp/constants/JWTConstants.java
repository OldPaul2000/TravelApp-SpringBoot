package com.travelapp.travelapp.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTConstants {

    @Value("${jwt.secret-key}")
    private String SECRET_KEY;

    @Value("${jwt.header}")
    private String HEADER;

    public JWTConstants() {}

    public String getSECRET_KEY() {
        return SECRET_KEY;
    }

    public String getHEADER() {
        return HEADER;
    }
}
