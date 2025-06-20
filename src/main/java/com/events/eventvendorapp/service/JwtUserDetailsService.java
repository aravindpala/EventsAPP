package com.events.eventvendorapp.service;


import com.events.eventvendorapp.model.vendor.Vendor;
import com.events.eventvendorapp.repository.vendor.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private VendorRepository vendorRepository;

    @Override
    public UserDetails loadUserByUsername(String emailOrPhone) throws UsernameNotFoundException {
        Vendor vendor = vendorRepository.findById(emailOrPhone)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(
                vendor.getId(),
                "",  // password is not used
                Collections.emptyList()
        );

    }

}
