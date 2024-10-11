package com.barber.Controllers;

import com.barber.Entities.Dtos.SchedulingDTO;
import com.barber.Services.SchedulingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/Scheduling")
public class SchedulingController {

    @Autowired
    private SchedulingService service;


    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<SchedulingDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.findAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity create(@Valid @RequestBody SchedulingDTO data) {
        service.create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body("Agendamento feito com sucesso!");
    }
}
