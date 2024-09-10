package com.barber.Entities;

import com.barber.Entities.Dtos.UserDTO;
import com.barber.Entities.Enums.UserType;
import jakarta.persistence.*;
import lombok.Getter;


import java.io.Serializable;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User  {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @Column(name = "email", length = 150, nullable = false)
    private String email;
    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "type", length = 100, nullable = false)
    private UserType type;

    @Column(name = "active", length = 20, nullable = false)
    private boolean active;

    public  User(){}
    public User(UserDTO userDTO) {
        this.name = userDTO.getName();
        this.email = userDTO.getEmail();
        this.password= userDTO.getPassword();
        this.type =userDTO.getType();
    }
}


