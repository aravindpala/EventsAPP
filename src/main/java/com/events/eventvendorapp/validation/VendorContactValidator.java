package com.events.eventvendorapp.validation;

import com.events.eventvendorapp.model.vendor.Vendor;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class VendorContactValidator implements ConstraintValidator<VendorContactConstraint, Vendor> {

    @Override
    public boolean isValid(Vendor vendor, ConstraintValidatorContext context) {
        if (vendor == null) return false;

        boolean hasEmail = vendor.getEmail() != null && !vendor.getEmail().isBlank();
        boolean hasMobile = vendor.getMobileNumber() != null && !vendor.getMobileNumber().isBlank();

        return hasEmail || hasMobile;
    }
}
