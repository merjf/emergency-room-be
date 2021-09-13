package com.portaleps.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN, ROLE_RESEARCHER;

    public String getAuthority() {
        return name();
    }

}