package com.example.events;

import lombok.Data;

@Data
public class OrderEvent {
    private String product;
    private int quantity;
}
