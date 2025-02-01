package com.travelapp.travelapp.security;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class Roles {

    public static final String USER = "USER";
    public static final String TOUR_GUIDE = "TOUR_GUIDE";
    public static final String ADMIN = "ADMIN";
    public static final String OWNER = "OWNER";

    private static final Map<String, String> roles = new HashMap<>(Map.ofEntries(
            new AbstractMap.SimpleEntry<>("USER", USER),
            new AbstractMap.SimpleEntry<>("TOUR_GUIDE", TOUR_GUIDE),
            new AbstractMap.SimpleEntry<>("ADMIN", ADMIN),
            new AbstractMap.SimpleEntry<>("OWNER", OWNER)
    ));

    public static Map<String, String> getRoles(){
        return new HashMap<>(roles);
    }

}
