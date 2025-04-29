package com.barber.Controllers;

import com.barber.Entities.Dtos.UserDTO;
import com.barber.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;


    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.findAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity create(@Valid @RequestBody UserDTO data) {
        service.create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso!");
    }

    @PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Integer id, @Valid @RequestBody UserDTO data) {
        service.update(id, data);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário atualizado com sucesso!");
    }

}
