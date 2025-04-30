package com.barber.Entities;

import com.barber.Entities.Dtos.ServiceDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @Column(name = "description", length = 150, nullable = false)
    private String description;

    @Column(name = "price", length = 150, nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "barber_id", referencedColumnName = "id", nullable = false)
    private Barber barber;

    @ManyToOne
    @JoinColumn(name = "barberShop_id", referencedColumnName = "id", nullable = false)
    private BarberShop barberShop;

    public Services(@Valid ServiceDTO serviceDTO) {
    }

    public Services(ServiceDTO service, Barber barber, BarberShop  barberShop  ) {
        this.id = service.getId();
        this.name = service.getName();
        this.description = service.getDescription();
        this.price = service.getPrice();
        this.barber = barber;
        this.barberShop = barberShop;
    }
}
