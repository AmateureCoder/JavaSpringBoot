package com.propane.libmanv1.identity.service.imp;
import com.propane.libmanv1.identity.model.Role;
import com.propane.libmanv1.identity.repository.RoleRepository;
import com.propane.libmanv1.identity.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepo;

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepo.findByName(name);
    }

    @Override
    public Role ensureRole(String name) {
        return roleRepo.findByName(name)
                .orElseGet(() -> roleRepo.save(Role.builder().name(name).build()));
    }
}