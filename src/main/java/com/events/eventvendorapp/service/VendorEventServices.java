package com.events.eventvendorapp.service;

import com.events.eventvendorapp.repository.vendor.EventServicesRepository;
import com.events.eventvendorapp.repository.vendor.VendorRepository;
import org.springframework.stereotype.Service;

@Service
public class VendorEventServices {

    private final VendorRepository vendorRepository;
    private final EventServicesRepository eventServicesRepository;

    public VendorEventServices(VendorRepository vendorRepository, EventServicesRepository eventServicesRepository) {
        this.vendorRepository = vendorRepository;
        this.eventServicesRepository = eventServicesRepository;
    }

//    public void addVendorServices(Ve){
//
//    }


}
