package com.travelapp.travelapp.service;

import com.travelapp.travelapp.constants.Roles;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Prevents current logged in user from making
// modifications(e.g. deleting a photo or comment etc) to another user's related data
// by introducing other id in the rest path other than current user's id.
// Just a user with high privilege is allowed to delete other user's data if the data doesn't
// correspond with app's policy

@Service
public class UserPrivilegesVerifier {

    public boolean isCurrentUser(String username){
        boolean isCurrentUser = SecurityContextHolder.getContext().getAuthentication().getName().equalsIgnoreCase(username);
        System.out.println("Is current user: " + isCurrentUser);
        return isCurrentUser;
    }

    // If the user has higher privileges(ADMIN, OWNER) he can remove other user's
    // photos, collages, comments or other posts if those posts are not appropriate
    // for others
    private boolean hasPrivileges;
    private final List<String> HIGH_PRIVILEGE_ROLES = new ArrayList<>(List.of(Roles.ADMIN_OWNER));
    public boolean hasEnoughPrivileges(){
        hasPrivileges = false;
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach(role -> {
           if(HIGH_PRIVILEGE_ROLES.contains(role.toString().substring(5))){
               System.out.println(role);
               hasPrivileges = true;
           }
        });
        System.out.println("Enough privileges: " + hasPrivileges);
        return hasPrivileges;
    }

}
