package com.nast.domain.entities.security;

public enum UserProfileType {
    USER("USER"),
    DBA("DBA"),
    ADMIN("ADMIN");

    private String userProfileType;

    UserProfileType(String userProfileType) {
        this.userProfileType = userProfileType;
    }

    public String getUserProfileType() {
        return userProfileType;
    }

}
