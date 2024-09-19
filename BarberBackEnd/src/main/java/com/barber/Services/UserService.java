package com.barber.Services;

import com.barber.Entities.Dtos.UserDTO;
import com.barber.Entities.User;
import com.barber.Exceptions.EmailAlreadyExistsException;
import com.barber.Infra.Security.TokenService;
import com.barber.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmailService emailService;

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

    public void sendRecoveryEmail(String email) {
        User user = (User) repository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        String token = tokenService.generateToken(user);
        String link = "https://seuapp.com/reset-password?token=" + token;

        String subject = "Recuperação de Senha";
        String body = "Olá " + user.getName() + ",\n\n" +
                "Clique no link abaixo para redefinir sua senha:\n" +
                link + "\n\n" +
                "Se você não solicitou a recuperação de senha, ignore este email.";

        try {
            emailService.send(user.getEmail(), subject, body);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao enviar o email de recuperação de senha.", e);
        }
    }

    public boolean resetPassword(String token, String newPassword) {
        String email = tokenService.ValidateToken(token);
        if (email.isEmpty()) {
            return false;
        }

        User user = (User) repository.findByEmail(email);
        if (user == null) {
            return false;
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        repository.save(user);
        return true;
    }
}
