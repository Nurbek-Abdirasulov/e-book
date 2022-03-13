package com.ebook.repositories;

import com.ebook.entities.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepsitory extends JpaRepository<Vendor, Long> {
    Optional<Vendor> findByVendorname(String vendorname);

}
