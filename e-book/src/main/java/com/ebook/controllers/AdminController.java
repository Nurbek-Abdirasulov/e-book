package com.ebook.controllers;

import com.ebook.entities.User;
import com.ebook.services.RoleService;
import com.ebook.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/admin")
public class AdminController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    private ResponseEntity<List<User>> getAllUsers() {
        try {
            return new ResponseEntity<>(userService.findByRole("ROLE_USER"), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/vendors")
    private ResponseEntity<List<User>> getAllVendors() {
        try {
            return new ResponseEntity<>(userService.findByRole("ROLE_VENDOR"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @CrossOrigin
    public String deleteById(@PathVariable("id") Long userId) {
        try {
            userService.deleteUser(userId);
            return "User with ID = " + userId + " was successfully deleted.";
        } catch (Exception e) {
            return String.valueOf(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity<User> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
