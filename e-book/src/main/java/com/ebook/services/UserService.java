package com.ebook.services;

import com.ebook.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    void deleteUser(Long id);

    User getById(Long id);

    Optional<User> findByUsername(String username);

    User saveUser(User user);

    List<User> findByRole(String role);

    User getUserById(Long id);

    User updateUser(User user);
}
