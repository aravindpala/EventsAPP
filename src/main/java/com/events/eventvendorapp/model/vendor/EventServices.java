package com.events.eventvendorapp.model.vendor;

import com.events.eventvendorapp.model.vendor.EventRating;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "event_services")
public class EventServices {

    @Id
    private String id;

    private String serviceName;   // e.g., Photography, Catering
    private List<String> tags;            // e.g., "Destination Wedding", "South Indian"
    private List<String> features; // e.g., "Drone", "Album", "Lighting"
    private List<EventRating> reviews = new ArrayList<>();
    private double averageRating;
    private String vendorId;    // Reference to the vendor providing this service
    private String description;
    private double minPrice;
    private double maxPrice;
    private double discountPercentage;
    private List<String> images;


}
