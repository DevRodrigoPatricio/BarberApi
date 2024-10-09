package com.barber.Services;

import com.barber.Entities.Enums.UserRoles;
import com.barber.Entities.User;
import com.barber.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        User admin = (User) userRepository.findByEmail("admin@gmail.com");
        if (admin == null) {
            admin = new User();
            admin.setEmail("admin@gmail.com");
            admin.setName("Admin");
            admin.setPassword(passwordEncoder.encode("senha123"));
            admin.setRole(UserRoles.ADMIN);
            admin.setActive(true);
            userRepository.save(admin);
        }
    }
}