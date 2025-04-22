package com.barber.Entities;

import com.barber.Entities.Dtos.OperationDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    private String dayOfTheWeek;
    private LocalTime openingHours;
    private LocalTime closingTime;

    @ManyToMany(mappedBy = "operations")
    private List<BarberShop> barberShops;


    public Operation(OperationDTO operation) {
        this.dayOfTheWeek = operation.dayOfTheWeek();
        this.openingHours = operation.openingHours();
        this.closingTime = operation.closingTime();
    }
}
