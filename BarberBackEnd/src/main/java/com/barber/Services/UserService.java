package com.barber.Services;

import com.barber.Entities.Dtos.UserDTO;
import com.barber.Entities.User;
import com.barber.Exceptions.EmailAlreadyExistsException;
import com.barber.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public UserDTO create(UserDTO userDto){


        UserDetails existingUser = repository.findByEmail(userDto.getEmail());
        if (existingUser != null) {
            throw new EmailAlreadyExistsException("E-mail já está em uso.");
        }
        userDto.setPassword( passwordEncoder.encode(userDto.getPassword()));
        User user = new User(userDto);
        repository.save(user);
        return new UserDTO(user);

    }
}
