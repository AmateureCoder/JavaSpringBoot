package com.propane.libmanv1.identity.service.imp;
import com.propane.libmanv1.identity.model.User;
import com.propane.libmanv1.identity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user with email: " + email));

        // your User entity already has Set<Role> which implement GrantedAuthority
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())          // Spring’s User.UserBuilder
                .password(user.getPassword())
                .authorities(user.getRoles())
                .build();
    }
}