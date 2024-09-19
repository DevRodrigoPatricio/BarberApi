package com.barber.Entities;

import com.barber.Entities.Enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
public class Scheduling {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "barber_id")
    private Barber barber;

    @ManyToMany
    @JoinTable(
            name = "scheduling_service", // Nome da tabela de junção
            joinColumns = @JoinColumn(name = "scheduling_id"), // Coluna de junção para Scheduling
            inverseJoinColumns = @JoinColumn(name = "service_id") // Coluna de junção para Service
    )
    private Set<Services> services;

    private Date date;
    private Status status;
}
