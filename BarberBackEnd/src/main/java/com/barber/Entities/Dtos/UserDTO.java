package com.barber.Entities.Dtos;

import com.barber.Entities.Enums.UserRoles;
import com.barber.Entities.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private int id;
    private String name;
    private String email;
    private String password;
    private UserRoles role;
    private boolean active;

    public UserDTO(){}

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password= user.getPassword();
        this.role =user.getRole();
        this.active = user.isActive();
    }
}
