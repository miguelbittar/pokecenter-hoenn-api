package dev.miguel.pokecenter_hoenn_api.config;

import dev.miguel.pokecenter_hoenn_api.entity.User;
import dev.miguel.pokecenter_hoenn_api.repository.UserRepository;
import dev.miguel.pokecenter_hoenn_api.util.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DatabaseSeeder {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${app.admin.username}")
    private String adminUsername;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Value("${app.admin.name}")
    private String adminName;

    @EventListener(ApplicationReadyEvent.class)
    public void seedDefaultAdmin() {
        System.out.println("Application fully initialized! Checking default admin...");

        Optional<User> existingAdmin = userRepository.findByUsername(adminUsername);

        if(existingAdmin.isEmpty()){
            System.out.println("Creating default admin...");

            User admin = new User();
            admin.setUsername(adminUsername);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setName(adminName);
            admin.setRole(UserRole.ADMIN);

            userRepository.save(admin);
            System.out.println("Default admin created successfully: " + adminUsername);
        } else {
            System.out.println("Admin already exists, skipping creation...");
        }
    }

}
