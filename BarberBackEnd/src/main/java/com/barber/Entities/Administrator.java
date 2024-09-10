package com.barber.Entities;
import com.barber.Entities.Dtos.UserDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Administrator extends User {

    @Column(name = "functionsAdm", length = 255, nullable = false)
    private String functionsAdm;

    public Administrator(UserDTO userDTO) {
        super(userDTO);
    }
}
