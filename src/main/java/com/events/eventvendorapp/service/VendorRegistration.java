package com.events.eventvendorapp.service;


import com.events.eventvendorapp.dto.vendor.BusinessDetailsDTO;
import com.events.eventvendorapp.model.vendor.Vendor;
import com.events.eventvendorapp.repository.vendor.VendorRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;

@Service
public class VendorRegistration {

    private final VendorRepository vendorRepository;
    private final Random random = new Random();

    public VendorRegistration(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public String generateOtp() {
        return String.format("%06d", random.nextInt(1_000_000));
    }

    public Vendor registerVendor(Vendor vendor) {

        if (vendor.getEmail() != null && vendorRepository.findByEmail(vendor.getEmail()) != null) {
            throw new IllegalArgumentException("Email is already registered");
        }


        if (vendor.getMobileNumber() != null && vendorRepository.findByMobileNumber(vendor.getMobileNumber()) != null) {
            throw new IllegalArgumentException("Mobile number is already registered");
        }
        long expiry = Instant.now().plusSeconds(300).toEpochMilli();// 5 minutes expiry
        if(vendor.getEmail() != null){
            vendor.setEmailOtp(generateOtp());
            vendor.setEmailOtpExpiry(expiry);
            vendor.setEmailVerified(false);
        }

        if(vendor.getMobileNumber() != null){
            vendor.setMobileOtp(generateOtp());
            vendor.setMobileOtpExpiry(expiry);
            vendor.setMobileVerified(false);
        }
        return vendorRepository.save(vendor);
    }

    public boolean verifyEmailOtp(String id, String otp) {
        var vendorOpt = vendorRepository.findById(id);
        if (vendorOpt.isEmpty()) return false;

        Vendor vendor = vendorOpt.get();
        if (vendor.getEmailOtp() == null) return false;
        if (vendor.getEmailOtpExpiry() < Instant.now().toEpochMilli()) return false;
        if (!vendor.getEmailOtp().equals(otp)) return false;

        vendor.setEmailVerified(true);
        vendor.setEmailOtp(null);
        vendor.setEmailOtpExpiry(0);
        vendorRepository.save(vendor);
        return true;
    }

    public void updateVendorToken(String vendorId, String token) {
        var vendorOpt = vendorRepository.findById(vendorId);
        if (vendorOpt.isEmpty()) {
            throw new IllegalArgumentException("Vendor not found");
        }

        Vendor existingVendor = vendorOpt.get();
        existingVendor.setToken(token);
        vendorRepository.save(existingVendor);
    }






    public boolean verifyMobileOtp(String vendorId, String otp) {
        var vendorOpt = vendorRepository.findById(vendorId);
        if (vendorOpt.isEmpty()) return false;

        Vendor vendor = vendorOpt.get();
        if (vendor.getMobileOtp() == null) return false;
        if (vendor.getMobileOtpExpiry() < Instant.now().toEpochMilli()) return false;
        if (!vendor.getMobileOtp().equals(otp)) return false;

        vendor.setMobileVerified(true);
        vendor.setMobileOtp(null);
        vendor.setMobileOtpExpiry(0);
        vendorRepository.save(vendor);
        return true;
    }

    public void addVendorBusinessDetails(BusinessDetailsDTO businessDetailsDTO){
        var vendorOpt = vendorRepository.findById(businessDetailsDTO.getVendorId());
        if(vendorOpt.isEmpty()) throw new IllegalArgumentException("Vendor Not Found");
        Vendor vendor = vendorOpt.get();
        vendor.setBusinessName(businessDetailsDTO.getBusinessName());
        vendor.setOwnerName(businessDetailsDTO.getOwnerName());
        vendor.setExperienceYears(businessDetailsDTO.getExperienceYears());
        vendor.setBusinessType(businessDetailsDTO.getBusinessType());
        vendor.setDescription(businessDetailsDTO.getDescription());
        vendor.setWebsiteURL(businessDetailsDTO.getWebsiteURL());
        vendor.setLocations(businessDetailsDTO.getLocations());
        vendorRepository.save(vendor);
    }
}

