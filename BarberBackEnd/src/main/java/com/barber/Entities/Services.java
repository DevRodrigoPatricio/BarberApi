package com.barber.Entities;

import com.barber.Entities.Dtos.ServiceDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Services {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @Column(name = "description", length = 150, nullable = false)
    private String  description;

    @Column(name = "price", length = 150, nullable = false)
    private BigDecimal price;


    public Services(){}
    public Services(ServiceDTO service){
        this.id= service.getId();
        this.name = service.getName();
        this.description =service.getDescription();
        this.price = service.getPrice();
    }
}
