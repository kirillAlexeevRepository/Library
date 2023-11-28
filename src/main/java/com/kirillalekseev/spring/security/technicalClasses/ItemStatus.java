package com.kirillalekseev.spring.security.technicalClasses;

public enum ItemStatus {

    IN_LIBRARY("In Library"),
    ALREADY_TAKEN("already taken"),
    REQUESTED_TO_RETURN("requested to return"),
    REQUESTED_TO_TAKE("requested to take");
    private final String displayName;

    ItemStatus(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
}
