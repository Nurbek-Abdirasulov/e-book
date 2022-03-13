package com.ebook.controllers.registration;


import com.ebook.entities.Role;
import com.ebook.entities.Vendor;
import com.ebook.services.RoleService;
import com.ebook.services.VendorService;
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
    private RoleService roleService;
    private VendorService vendorService;

    @Autowired
    public VendorRegistration(RoleService roleService,VendorService vendorService ) {
        this.roleService = roleService;
        this.vendorService=vendorService;

    }

    @CrossOrigin
    @PostMapping("/vendor/")
    public ResponseEntity<Vendor> vendorRegistration(@RequestBody Vendor vendor) {
        try {
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.getRoleByName("ROLE_VENDOR"));
            vendor.setRoles(roles);
            return new ResponseEntity<>(
                    vendorService.saveVendor(vendor), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
