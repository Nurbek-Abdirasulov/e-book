package com.ebook.services.Impl;

import com.ebook.entities.Role;
import com.ebook.repositories.RoleRepository;
import com.ebook.services.RoleService;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleByName(String role) {
        return roleRepository.getRoleByName(role);
    }
}
