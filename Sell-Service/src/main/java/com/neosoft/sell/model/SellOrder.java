package com.neosoft.sell.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SellOrder {
    private int id;
    private double quantity;
    private double price;
    private String symbol;
}
