package com.events.eventvendorapp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = VendorContactValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface VendorContactConstraint {
    String message() default "Either email or mobile number must be provided";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
