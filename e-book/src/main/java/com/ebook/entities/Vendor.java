package com.ebook.entities;

import lombok.Data;


import javax.persistence.*;

import java.util.Set;

@Entity
@Data
@Table(name = "vendors")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "vendor_name")
    private String vendorname;

    @Column(name = "vendor_last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "vendor_email")
    private String email;

    @Column(name = "vendor_number")
    private Long number;

    @ManyToMany
    @JoinTable(name = "vendors_roles",
            joinColumns = @JoinColumn(name = "vendor_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


}
