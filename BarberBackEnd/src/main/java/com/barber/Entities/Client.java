package com.barber.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Client extends  User  {
    @Column(name = "telephone", length = 20, nullable = false)
    private String telephone;


}
