package com.ebook.services.Impl;

import com.ebook.entities.User;
import com.ebook.repositories.UserRepository;
import com.ebook.services.UserService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    @Override
    public User getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "user with id = " + id + " does not exists"
                ));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public User saveUser (User user) {
        return repository.save(user);
    }

    @Override
    public List<User> findByRole (String role) {
        return repository.findAllByRole(role);
    }

    @Override
    public User getUserById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public User updateUser(User user) {
        return repository.save(user);
    }
}
