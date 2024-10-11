package com.barber.Entities.Enums;

import lombok.Getter;

@Getter
public enum Status {
    Scheduled("Agendado"),
    Completed("Concluído"),
    Canceled("Cancelado"),
    ;
    private final String status;

    Status(String status) {
        this.status = status;
    }

}
