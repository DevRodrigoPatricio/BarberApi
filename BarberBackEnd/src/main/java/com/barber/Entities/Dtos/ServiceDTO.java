package com.barber.Entities.Dtos;

import com.barber.Entities.Services;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDTO {

    @JsonProperty("id")
    private int id;

    @NotNull(message = "deve-se ser informado o nome do serviço!")
    @NotBlank(message = "deve-se ser informado o nome do serviço!")
    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @NotNull(message = "deve-se ser informado o valor!")
    @JsonProperty("price")
    private BigDecimal price;

    @NotNull(message = "deve-se ser informado o id do barbeiro!")
    @JsonProperty("barber")
    private int barber;

    public ServiceDTO(Services service) {
        this.id = service.getId();
        this.name = service.getName();
        this.description = service.getDescription();
        this.price = service.getPrice();
        this.barber = service.getBarber().getId();
    }
}
