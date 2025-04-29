package com.barber.Controllers;

import com.barber.Entities.Dtos.BarberDTO;
import com.barber.Services.BarberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/barbers")
public class BarberController {

    @Autowired
    private BarberService service;


    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<BarberDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.findAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
    @GetMapping(value ="/{idBarberShop}")
    public ResponseEntity<List<BarberDTO>> findByBarberShop(@PathVariable Integer idBarberShop) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.findByBarberShopid(idBarberShop));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity create(@Valid @RequestBody BarberDTO data) {
        service.create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body("Barbeiro criado com sucesso!");
    }

    @PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Integer id, @Valid @RequestBody BarberDTO data) {
        service.update(id, data);
        return ResponseEntity.status(HttpStatus.CREATED).body("Barbeiro atualizado com sucesso!");
    }
}
