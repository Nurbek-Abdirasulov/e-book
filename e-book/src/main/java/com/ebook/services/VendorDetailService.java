package com.ebook.services;
import com.ebook.entities.Role;
import com.ebook.entities.Vendor;
import com.ebook.repositories.VendorRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class VendorDetailService implements UserDetailsService{
    private VendorRepsitory vendorRepsitory;

    @Autowired
    public void setVendorRepsitory(VendorRepsitory vendorRepsitory) {
        this.vendorRepsitory = vendorRepsitory;
    }

    Optional<Vendor> findByVendorname(String vendorname){
        return vendorRepsitory.findByVendorname(vendorname);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Vendor vendor = findByVendorname(username).orElseThrow(()-> new UsernameNotFoundException(String.format("Vendor '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(vendor.getVendorname(), vendor.getPassword(), mapRolesToAuthorities(vendor.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
