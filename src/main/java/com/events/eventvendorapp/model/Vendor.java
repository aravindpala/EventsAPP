package com.events.eventvendorapp.model;


import com.events.eventvendorapp.validation.VendorContactConstraint;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.lang.NonNull;

@Data
@Document(collection = "vendors")
@VendorContactConstraint
public class Vendor {

    @Id
    private String id;

   // private String name;
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

}
