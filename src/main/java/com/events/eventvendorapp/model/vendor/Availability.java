package com.events.eventvendorapp.model.vendor;



import lombok.Data;

@Data
public class Availability {
    private String dayOfWeek;   // e.g., "Monday", "Saturday"
    private String startTime;   // e.g., "09:00"
    private String endTime;
}
