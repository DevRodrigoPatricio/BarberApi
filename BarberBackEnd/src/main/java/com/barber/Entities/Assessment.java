package com.barber.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Assessment {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany
    @JoinTable(
            name = "scheduling_service", // Nome da tabela de junção
            joinColumns = @JoinColumn(name = "scheduling_id"), // Coluna de junção para Scheduling
            inverseJoinColumns = @JoinColumn(name = "service_id") // Coluna de junção para Service
    )
    private Set<Service> services;
    private int  notice;


    @Column(name = "comment", length = 255, nullable = true)
    private String comment;
}
