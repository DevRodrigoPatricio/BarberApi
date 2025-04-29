package com.barber.Entities.Dtos;


import com.barber.Entities.Barber;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @NotNull(message = "deve ser informado o id da barbearia!")
    @JsonProperty("barberShop")
    private int barberShop;


    public BarberDTO(Barber barber) {
        this.id = barber.getId();
        this.name = barber.getName();
        this.specialties = barber.getSpecialties();
    }


}
