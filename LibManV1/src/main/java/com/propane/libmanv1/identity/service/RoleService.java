package com.propane.libmanv1.identity.service;
import com.propane.libmanv1.identity.model.Role;
import java.util.Optional;
public interface RoleService {
    Optional<Role> findByName(String name);
    Role ensureRole(String name);
}
