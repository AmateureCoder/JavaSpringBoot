package com.propane.libmanv1.identity.service;
import com.propane.libmanv1.auth.dto.RegistrationDto;
public interface UserService {
    void register(RegistrationDto dto);
    void updateRoles(Long userId, String roleName);
}