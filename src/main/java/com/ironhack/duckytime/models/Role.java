package com.ironhack.duckytime.models;

public enum Role {
    ADMIN("ROLE_ADMIN"),
    SUPERADMIN("ROLE_SUPERADMIN"),
    HOUSEHOLD("ROLE_HOUSEHOLD");

    public final String label;

    private Role(String label) {
        this.label = label;
    }
}
