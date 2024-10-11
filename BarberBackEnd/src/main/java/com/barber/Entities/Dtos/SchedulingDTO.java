package com.barber.Entities.Dtos;

import com.barber.Entities.Enums.Status;
import com.barber.Entities.Scheduling;
import com.barber.Entities.Services;
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

    private int user;

    private int barber;

    private Set<Integer> services;

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
