package com.events.eventvendorapp.repository;

import com.events.eventvendorapp.model.Vendor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface VendorRepository extends MongoRepository<Vendor, String> {
    Vendor findByEmail(String email);
    Vendor findByMobileNumber(String mobileNumber);
    Optional<Vendor> findByEmailOrMobileNumber(String email, String mobileNumber);
}
