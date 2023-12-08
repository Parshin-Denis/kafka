package com.example.events;

import lombok.Data;

import java.time.Instant;

@Data
public class OrderStatusEvent {
    private String status;
    private Instant date;
}
