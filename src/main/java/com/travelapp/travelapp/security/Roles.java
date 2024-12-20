package com.travelapp.travelapp.security;

public enum Roles {

    OWNER(1),
    ADMIN(2),
    USER(3);

    public int permissionLevel;

    Roles(int permissionLevel){
        this.permissionLevel = permissionLevel;
    }

}
