package com.barber.Entities;

import com.barber.Entities.Dtos.BarberDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Barber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 150, nullable = false)
    private String name;
    @Column(name = "specialties", length = 255, nullable = true)
    private String specialties;

    @ManyToOne
    @JoinColumn(name = "barberShop_id", referencedColumnName = "id", nullable = false)
    private BarberShop barberShop;


    public Barber(BarberDTO barberDTO) {
        this.name = barberDTO.getName();
        this.specialties = barberDTO.getSpecialties();
    }
}
