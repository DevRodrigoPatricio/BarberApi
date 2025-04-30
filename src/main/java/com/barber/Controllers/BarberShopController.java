package com.barber.Controllers;


import com.barber.Entities.Dtos.CreateBarberShopDTO;
import com.barber.Services.BarberShopService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/barberShop")
public class BarberShopController {

    @Autowired
    private BarberShopService barberShop;

    @PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
    @GetMapping
    public ResponseEntity<List<CreateBarberShopDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.CREATED).body(barberShop.getAllBarberShops());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @PostMapping
    public ResponseEntity create(@Valid @RequestBody CreateBarberShopDTO data) {
        barberShop.createBarberShop(data);
        return ResponseEntity.status(HttpStatus.CREATED).body("Barbearia criada com sucesso!");
    }
}
