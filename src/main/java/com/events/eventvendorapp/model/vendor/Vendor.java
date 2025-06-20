package com.events.eventvendorapp.model.vendor;


import com.events.eventvendorapp.validation.VendorContactConstraint;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Document(collection = "vendors")
@VendorContactConstraint
public class Vendor {

    @Id
    private String id;

    @Indexed(unique = true,sparse = true)
    private String email;
    @Indexed(unique = true,sparse = true)
    private String mobileNumber;
    private boolean emailVerified = false;
    private boolean mobileVerified = false;
    private String emailOtp;
    private String mobileOtp;
    private long emailOtpExpiry;  // timestamp in millis
    private long mobileOtpExpiry;
    private boolean profileCompleted = false;
    private String alternativeMobileNumber;

    // Section 1
    private String businessName;
    private String ownerName;
    private String businessType;
    private int experienceYears;
    private String description;
    private String websiteURL;
    private VendorVerification verification;
    private VendorPayment accountDetails;
    private LocalDateTime createdAt = LocalDateTime.now();
    private String token;
    private List<String> Locations;



}
