package com.events.eventvendorapp.dto.vendor;


import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.List;

@Getter
@Setter
public class BusinessDetailsDTO {

    @NonNull
    private String vendorId;
    @NonNull
    private String businessName;
    @NonNull
    private String ownerName;
    @NonNull
    private String businessType;
    @NonNull
    private int experienceYears;
    private String description;
    private String websiteURL;

    @NonNull
    private List<String> locations;
}
