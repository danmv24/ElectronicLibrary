package com.example.ElectronicLibrary.enums;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public enum Role implements GrantedAuthority {

    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_ADMIN");

    private final String value;

    @Override
    public String getAuthority() {
        return value;
    }
}
