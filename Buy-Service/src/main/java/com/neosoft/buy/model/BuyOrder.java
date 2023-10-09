package com.neosoft.buy.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BuyOrder {
    private int id;
    private double quantity;
    private double price;
    private String symbol;
}
