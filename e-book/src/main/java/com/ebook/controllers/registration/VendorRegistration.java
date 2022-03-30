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

@CrossOrigin
@RestController
@RequestMapping("/api/registration")
public class VendorRegistration {
    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public VendorRegistration(RoleService roleService, UserService userService ) {
        this.roleService = roleService;
        this.userService=userService;

    }

    @CrossOrigin
    @PostMapping("/vendor/")
    public ResponseEntity<User> vendorRegistration(@RequestBody  User user) {
        try {
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.getRoleByName("ROLE_VENDOR"));
            user.setRoles(roles);
            return new ResponseEntity<>(
                    userService.saveUser(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
