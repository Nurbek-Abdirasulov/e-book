package com.ebook.services;

import com.ebook.entities.Vendor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface VendorService {
    void addVendor(Vendor vendor);
    List<Vendor> listVendors();
    void deleteVendor(Long id);
    Vendor getById(Long id);
    Optional<Vendor> findByVendorname(String username);
    Vendor saveUser(Vendor vendor);
}
