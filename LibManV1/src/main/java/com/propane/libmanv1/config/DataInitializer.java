package com.propane.libmanv1.config;
import com.propane.libmanv1.identity.model.Role;
import com.propane.libmanv1.identity.model.User;
import com.propane.libmanv1.identity.repository.RoleRepository;
import com.propane.libmanv1.identity.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        Role adminRole = roleRepo.findByName("ROLE_ADMIN")
                .orElseGet(() -> roleRepo.save(Role.builder().name("ROLE_ADMIN").build()));

        Role userRole = roleRepo.findByName("ROLE_USER")
                .orElseGet(() -> roleRepo.save(Role.builder().name("ROLE_USER").build()));

        if (userRepo.findByEmail("admin@bookmanager.com").isEmpty()) {
            User admin = User.builder()
                    .username("admin")
                    .email("admin@bookmanager.com")
                    .password(encoder.encode("admin123"))
                    .roles(Set.of(adminRole))
                    .build();
            userRepo.save(admin);
        }
    }
}