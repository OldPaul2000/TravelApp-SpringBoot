package com.travelapp.travelapp.constants;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Roles {

    public static final String USER = "USER";
    public static final String TOUR_GUIDE = "TOUR_GUIDE";
    public static final String ADMIN = "ADMIN";
    public static final String OWNER = "OWNER";

    public static final String[] USER_ADMIN = {USER, ADMIN};
    public static final String[] USER_OWNER = {USER, OWNER};
    public static final String[] ADMIN_OWNER = {OWNER, ADMIN};
    public static final String[] USER_ADMIN_OWNER = {USER, ADMIN, OWNER};
    public static final String[] TOUR_GUIDE_ADMIN = {TOUR_GUIDE, ADMIN};
    public static final String[] TOUR_GUIDE_ADMIN_OWNER = {TOUR_GUIDE, ADMIN, OWNER};
    public static final String[] ALL_ROLES = {USER, TOUR_GUIDE, ADMIN, OWNER};

    private static final List<String> ROLES_LIST = new ArrayList<>(List.of(
            USER,
            TOUR_GUIDE,
            ADMIN,
            OWNER
    ));

    public static boolean exists(String role){
        return ROLES_LIST.contains(role);
    }

}
