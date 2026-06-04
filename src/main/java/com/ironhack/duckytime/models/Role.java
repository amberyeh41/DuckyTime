package com.ironhack.duckytime.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    SUPERADMIN,
    HOUSEHOLD;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
