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

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        service.sendRecoveryEmail(email);
        return ResponseEntity.ok("Email de recuperação enviado com sucesso.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        boolean success = service.resetPassword(token, newPassword);
        if (success) {
            return ResponseEntity.ok("Senha redefinida com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token inválido ou expirado.");
        }
    }

}
