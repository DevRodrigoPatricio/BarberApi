package com.barber.Controllers;


import com.barber.Entities.Dtos.ServiceDTO;
import com.barber.Services.ServicesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(value = "/services")
public class ServiceController {

    @Autowired
    private ServicesService service;

    @PostMapping
    public ResponseEntity<ServiceDTO> create(@Valid @RequestBody ServiceDTO serviceDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(serviceDto));
    }
}
