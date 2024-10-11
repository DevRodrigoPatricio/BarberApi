package com.barber.Entities;

import com.barber.Entities.Enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Scheduling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "barber_id")
    private Barber barber;

    @ManyToMany
    @JoinTable(
            name = "scheduling_service",
            joinColumns = @JoinColumn(name = "scheduling_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private Set<Services> services;

    private Date date;
    private Status status;


}
