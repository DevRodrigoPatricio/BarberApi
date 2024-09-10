package com.barber.Entities.Dtos;

import com.barber.Entities.Services;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ServiceDTO {
    private int id;

    private String name;

    private String  description;

    private BigDecimal price;

    public  ServiceDTO(){}
    public  ServiceDTO(Services service){
        this.id= service.getId();
        this.name = service.getName();
        this.description =service.getDescription();
        this.price = service.getPrice();
    }
}
