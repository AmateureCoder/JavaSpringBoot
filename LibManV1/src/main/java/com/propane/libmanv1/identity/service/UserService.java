package com.propane.libmanv1.identity.service;
import com.propane.libmanv1.auth.dto.RegistrationDto;
import com.propane.libmanv1.identity.model.User;

public interface UserService {
    void register(RegistrationDto dto);
    void updateRoles(Long userId, String roleName);
    public void updateUser(String username, String name, String email);
    public void save(User user);
}