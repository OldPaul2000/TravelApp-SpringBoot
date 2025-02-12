package com.travelapp.travelapp.service;

import com.travelapp.travelapp.constants.Roles;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Prevents current logged in user from making
// modifications(e.g. deleting a photo or comment etc) to another user's related data
// by introducing other id in the rest path other than current user's id.
// Just a user with high privilege is allowed to delete other user's data if the data doesn't
// correspond with app's policy

@Service
public class CurrentUserVerifier {


    public boolean isCurrentUser(String username){
        return SecurityContextHolder.getContext().getAuthentication().getName().equals(username);
    }

    private boolean hasPrivileges;
    public boolean hasEnoughPrivileges(String... currentUserRoles){
        List<String> roles = new ArrayList<>(List.of(currentUserRoles));
        Arrays.stream(Roles.ADMIN_OWNER).forEach(role -> {
            if(roles.contains(role)){
                hasPrivileges = true;
            }
        });

        return hasPrivileges;
    }

}
