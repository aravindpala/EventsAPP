package com.events.eventvendorapp.controller.vendor;


import com.events.eventvendorapp.dto.vendor.BusinessDetailsDTO;
import com.events.eventvendorapp.security.JwtTokenUtil;
import com.events.eventvendorapp.service.JwtUserDetailsService;
import com.events.eventvendorapp.service.VendorEventServices;
import com.events.eventvendorapp.service.VendorRegistration;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vendor")
public class VendorController {
    // This class will handle the services related to vendors
    // It will include methods for adding, updating, and retrieving vendor services
    // as well as managing service categories and availability.

    private final VendorEventServices vendorEventServices;
    private final VendorRegistration vendorRegistration;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    public VendorController(VendorEventServices vendorEventServices, VendorRegistration vendorRegistration) {
        this.vendorEventServices = vendorEventServices;
        this.vendorRegistration = vendorRegistration;
    }

    @PostMapping("/addBusinessDetails")
    public ResponseEntity<?> addBusinessDetails(@Valid @RequestBody BusinessDetailsDTO businessDetailsRequest) {
        vendorRegistration.addVendorBusinessDetails(businessDetailsRequest);
        return ResponseEntity.ok("Business details added successfully");
    }




}
