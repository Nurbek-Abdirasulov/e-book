package com.ebook.controllers.registration;

import com.ebook.entities.Role;
import com.ebook.entities.User;
import com.ebook.services.RoleService;
import com.ebook.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/api/registration/user")
public class UserRegistration {
    private final UserService userService;
    private final RoleService roleService;
    @Autowired
    public UserRegistration(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        try {
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.getRoleByName("ROLE_USER"));
            user.setRoles(roles);
            return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
