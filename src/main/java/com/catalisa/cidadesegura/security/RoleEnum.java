package com.catalisa.cidadesegura.security;

public enum RoleEnum {

    USER("USER"),
    ADMIN("ADMIN");

    private String role;

    RoleEnum(String role) {
        this.role = role;
    }

    RoleEnum() {

    }

    public String getRole() {
        return role;
    }
}

