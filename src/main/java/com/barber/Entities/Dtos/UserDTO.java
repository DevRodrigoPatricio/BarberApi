package com.barber.Entities.Dtos;

import com.barber.Entities.Enums.UserRoles;
import com.barber.Entities.User;
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
public class UserDTO {

    @JsonProperty("id")
    private int id;

    @NotNull(message = "O nome do usuário deve-se ser informado!")
    @NotBlank(message = "O nome do usuário deve-se ser informado!")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "O email do usuário deve-se ser informado!")
    @NotBlank(message = "O email do usuário deve-se ser informado!")
    @JsonProperty("email")
    private String email;

    @NotNull(message = "A senha do usuário deve-se ser informado!")
    @NotBlank(message = "A senha do usuário deve-se ser informado!")
    @JsonProperty("password")
    private String password;

    @JsonProperty("role")
    private UserRoles role;

    @JsonProperty("active")
    private boolean active;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.active = user.isActive();
    }


}
