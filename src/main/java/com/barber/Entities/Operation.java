package com.barber.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    private String dayOfTheWeek;
    private LocalDateTime openingHours;
    private LocalDateTime closingTime;

    @ManyToMany(mappedBy = "operations")
    private List<BarberShop> barberShops;
}
