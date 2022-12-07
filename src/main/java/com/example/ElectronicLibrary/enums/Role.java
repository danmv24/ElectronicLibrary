package com.example.ElectronicLibrary.enums;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public enum Role implements GrantedAuthority {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_ADMIN");

    private final String vale;

    @Override
    public String getAuthority() {
        return vale;
    }
}
