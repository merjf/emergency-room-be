package com.portaleps.model.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN(0), ROLE_RESEARCHER(1);

    private int code;

    Role(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getAuthority() {
        return name();
    }

    public static int getRoleLength(){
        return Role.values().length;
    }
}