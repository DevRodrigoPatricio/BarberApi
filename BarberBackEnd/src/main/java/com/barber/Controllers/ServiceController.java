package com.barber.Controllers;


import com.barber.Entities.Dtos.ServiceDTO;
import com.barber.Services.ServicesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/services")
public class ServiceController {

    @Autowired
    private ServicesService service;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ServiceDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.findAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
    @GetMapping(value = "/{name}")
    public ResponseEntity<List<ServiceDTO>> findByName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.findByName(name));
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Integer id, @Valid @RequestBody ServiceDTO data){
        service.update(id,data);
        return  ResponseEntity.status(HttpStatus.CREATED).body("Serviço atualizado com sucesso!");
    }

    @PreAuthorize("hasAnyRole('ADMIN')")

    @PostMapping
    public ResponseEntity<ServiceDTO> create(@Valid @RequestBody ServiceDTO serviceDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(serviceDto));
    }
}
