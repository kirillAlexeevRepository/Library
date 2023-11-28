package com.kirillalekseev.spring.security.technicalClasses;

public enum abstractStatus {

    AVAILABLE("available"),
    NOT_AVAILABLE("not available");

    private final String displayName;

    abstractStatus(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
}
