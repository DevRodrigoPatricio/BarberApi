package com.barber.Services;

import com.barber.Entities.Dtos.ServiceDTO;
import com.barber.Entities.Dtos.UserDTO;
import com.barber.Entities.Services;
import com.barber.Entities.User;
import com.barber.Exceptions.EmailAlreadyExistsException;
import com.barber.Exceptions.ObjectnotFoundException;
import com.barber.Infra.Security.TokenService;
import com.barber.Repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


    public List<UserDTO> findAll() {
        List<User> services = repository.findAll();
        return services.stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public User findById(Integer id) {
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new ObjectnotFoundException("Objeto não econtrado " + id));
    }

    public UserDTO create(UserDTO userDTO) {
        UserDetails existingUser = repository.findByEmail(userDTO.getEmail());
        if (existingUser != null) {
            throw new EmailAlreadyExistsException("E-mail já está em uso.");
        }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = new User(userDTO);
        repository.save(user);
        return new UserDTO(user);

    }

    public User update(Integer id, @Valid UserDTO userDTO) {
        User oldUser = findById(id);
        if (!userDTO.getPassword().equals(oldUser.getPassword())) {
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        oldUser = new User(userDTO);
        oldUser.setId(id);
        return repository.save(oldUser);
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
