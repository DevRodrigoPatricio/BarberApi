package com.barber.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
public class BarberShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200,nullable = false)
    private String name;

    @Column(length = 20,nullable = false)
    private String cnpj;

    private String telephone;
    private String email;

    private String address;
    private String city;
    private String state;
    private String cep;

    @ManyToMany
    @JoinTable(
            name = "barbershop_operation",
            joinColumns = @JoinColumn(name = "barbershop_id"),
            inverseJoinColumns = @JoinColumn(name = "operation_id")
    )
    private List<Operation> operations;
    private Boolean ativa;





}
