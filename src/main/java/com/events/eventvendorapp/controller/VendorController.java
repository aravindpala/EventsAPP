package com.events.eventvendorapp.controller;


import com.events.eventvendorapp.dto.JwtResponse;
import com.events.eventvendorapp.dto.OtpVerificationRequest;
import com.events.eventvendorapp.model.Vendor;
import com.events.eventvendorapp.security.JwtTokenUtil;
import com.events.eventvendorapp.service.JwtUserDetailsService;
import com.events.eventvendorapp.service.VendorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/")
public class VendorController {

    private final VendorService vendorService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;


    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerVendor(@Valid @RequestBody Vendor vendor) {
        try {
            Vendor savedVendor = vendorService.registerVendor(vendor);
            return ResponseEntity.ok(savedVendor);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/verifyEmailOtp")
    public ResponseEntity<?> verifyEmailOtp(@RequestBody OtpVerificationRequest request) {
        boolean verified = vendorService.verifyEmailOtp(request.get_id(), request.getOtp());
        if (verified) {
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(request.get_id());
            String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token));
        }
        return ResponseEntity.badRequest().body("Invalid or expired email OTP");
    }

    @PostMapping("/{vendorId}/verify-mobile-otp")
    public ResponseEntity<String> verifyMobileOtp(@PathVariable String vendorId, @RequestParam String otp) {
        boolean verified = vendorService.verifyMobileOtp(vendorId, otp);
        if (verified) {
            return ResponseEntity.ok("Mobile OTP verified");
        }
        return ResponseEntity.badRequest().body("Invalid or expired mobile OTP");
    }

}
