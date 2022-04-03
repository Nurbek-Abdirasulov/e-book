package com.ebook.services;

import com.ebook.entities.Role;
import org.springframework.stereotype.Service;


@Service
public interface RoleService {
    Role getRoleByName(String name);
}
