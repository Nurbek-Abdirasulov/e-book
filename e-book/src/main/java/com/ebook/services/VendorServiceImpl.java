package com.ebook.services;

import com.ebook.entities.Vendor;
import com.ebook.repositories.VendorRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorServiceImpl implements VendorService{
    private final VendorRepsitory vendorRepository;
    @Autowired
    public VendorServiceImpl(VendorRepsitory vendorRepository) {
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
    public Optional<Vendor> findByVendorname(String vendorname) {
        return vendorRepository.findByVendorname(vendorname);
    }

    @Override
    public Vendor saveVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }
}
