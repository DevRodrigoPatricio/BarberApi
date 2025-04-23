package com.barber.Entities.Dtos;

import com.barber.Entities.Enums.Status;
import com.barber.Entities.Scheduling;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchedulingDTO {

    private int id;

    @NotNull(message = "deve-se ser informado o id do usuário!")
    @JsonProperty("user")
    private int user;

    @NotNull(message = "deve-se ser informado o id do barbeiro!")
    @JsonProperty("barber")
    private int barber;

    @NotNull(message = "deve-se ser informada a lista de ids de serviços!")
    @JsonProperty("services")
    private Set<Integer> services;

    @NotNull(message = "Informe a data do agendamento")
    @JsonProperty("date")
    private Date date;
    private Status status;

    public SchedulingDTO(Scheduling scheduling) {
        this.id = scheduling.getId();
        this.user = scheduling.getUser().getId();
        this.barber = scheduling.getBarber().getId();
        this.services = scheduling.getServices()
                .stream()
                .map(x -> x.getId())
                .collect(Collectors.toSet());
        this.date = scheduling.getDate();
        this.status = scheduling.getStatus();
    }


}
