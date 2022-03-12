package com.ebook.services;

import com.ebook.entities.Vendor;
import com.ebook.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorServiceImpl implements VendorService {
    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void addVendor(Vendor vendor) {
        vendorRepository.save(vendor);
    }

    @Override
    public List<Vendor> listVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }

    @Override
    public Vendor getById(Long id) {
        return vendorRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "user with id = " + id + " does not exists"
                ));
    }

    @Override
    public Optional<Vendor> findByVendorname(String username) {
        return vendorRepository.findByVendorname(username);
    }

    @Override
    public Vendor saveUser(Vendor vendor) {
        return vendorRepository.save(vendor);
    }
}
