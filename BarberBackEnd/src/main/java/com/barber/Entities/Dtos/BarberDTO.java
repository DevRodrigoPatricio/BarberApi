package com.barber.Entities.Dtos;

import com.barber.Entities.Barber;
import com.barber.Entities.Enums.UserRoles;
import com.barber.Entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BarberDTO {


    @JsonProperty("id")
    private int id;

    @NotNull(message = "O nome do barbeiro deve-se ser informado!")
    @NotBlank(message = "O nome do barbeiro deve-se ser informado!")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "O nome do usuário deve-se ser informado!")
    @NotBlank(message = "O nome do usuário deve-se ser informado!")
    @JsonProperty("specialties")
    private String specialties;


    public BarberDTO(){}

    public BarberDTO(Barber barber) {
        this.id = barber.getId();
        this.name = barber.getName();
        this.specialties = barber.getSpecialties();
    }


}
