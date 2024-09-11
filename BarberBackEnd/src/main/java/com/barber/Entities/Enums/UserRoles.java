package com.barber.Entities.Enums;

import lombok.Getter;

@Getter
public enum UserRoles {
    CLIENT("Cliente"),
    ADMIN("Administrador");
    private final String role;
    UserRoles(String role) {
        this.role = role;
    }

}
