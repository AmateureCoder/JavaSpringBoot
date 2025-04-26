package com.propane.libmanv1.identity.service.imp;
import com.propane.libmanv1.auth.dto.RegistrationDto;
import com.propane.libmanv1.identity.model.Role;
import com.propane.libmanv1.identity.model.User;
import com.propane.libmanv1.identity.repository.UserRepository;
import com.propane.libmanv1.identity.service.RoleService;
import com.propane.libmanv1.identity.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(RegistrationDto dto) {
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        Role userRole = roleService.ensureRole("ROLE_USER");
        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(Set.of(userRole))
                .build();
        userRepo.save(user);
    }

    @Override
    @Transactional
    public void updateRoles(Long userId, String roleName) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Role role = roleService.ensureRole(roleName);
        user.getRoles().add(role);
        userRepo.save(user);
    }
    public User getCurrentUser() {
        // Implement logic to retrieve the currently authenticated user
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found:" + username));

    }   
}