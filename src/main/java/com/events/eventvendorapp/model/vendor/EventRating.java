package com.events.eventvendorapp.model.vendor;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class EventRating {
    private String userId;
    private String userName;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
}

