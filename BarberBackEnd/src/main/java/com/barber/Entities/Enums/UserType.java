package com.barber.Entities.Enums;

import lombok.Getter;

@Getter
public enum UserType {
    CLIENT("Cliente"),
    ADMIN("Administrador");
    private final String type;
    UserType(String type) {
        this.type = type;
    }

}
