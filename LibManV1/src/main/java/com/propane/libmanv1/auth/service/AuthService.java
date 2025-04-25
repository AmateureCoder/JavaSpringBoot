package com.propane.libmanv1.auth.service;
import com.propane.libmanv1.auth.dto.RegistrationDto;
import com.propane.libmanv1.identity.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    public void register(@Valid RegistrationDto dto) {
        userService.register(dto);
    }
}
