package com.events.eventvendorapp.controller.vendor;


import com.events.eventvendorapp.dto.OtpVerificationRequest;
import com.events.eventvendorapp.dto.OtpVerificationResponse;
import com.events.eventvendorapp.model.vendor.Vendor;
import com.events.eventvendorapp.security.JwtTokenUtil;
import com.events.eventvendorapp.service.JwtUserDetailsService;
import com.events.eventvendorapp.service.VendorRegistration;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/")
public class VendorRegister {

    private final VendorRegistration vendorRegistration;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    private  OtpVerificationResponse response;

    public VendorRegister(VendorRegistration vendorRegistration) {
        this.vendorRegistration = vendorRegistration;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerVendor(@Valid @RequestBody Vendor vendor) {
        try {
            Vendor savedVendor = vendorRegistration.registerVendor(vendor);
            return ResponseEntity.ok(savedVendor);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/verifyEmailOtp")
    public ResponseEntity<?> verifyEmailOtp(@Valid @RequestBody OtpVerificationRequest request) {
        OtpVerificationResponse response = new OtpVerificationResponse();
        boolean verified = vendorRegistration.verifyEmailOtp(request.get_id(), request.getOtp());
        if (verified) {
            //UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(request.get_id());
            response.setMessage("Email OTP verified successfully");
            response.setId(request.get_id());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body("Invalid or expired email OTP");
    }

    @PostMapping("/verifyMobileOtp")
    public ResponseEntity<OtpVerificationResponse> verifyMobileOtp(@Valid @RequestBody OtpVerificationRequest request) {
        response = new OtpVerificationResponse();
        boolean verified = vendorRegistration.verifyMobileOtp(request.get_id(), request.getOtp());
        if (verified) {
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(request.get_id());
            response.setToken(jwtTokenUtil.generateToken(userDetails));
            response.setMessage("Mobile OTP verified successfully");
            response.setId(request.get_id());
            return ResponseEntity.ok(response);
        }
        response.setMessage("Invalid or expired mobile OTP");
        return ResponseEntity.badRequest().body(response);
    }

}
