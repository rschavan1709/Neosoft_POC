package com.neosoft.matching.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class BuyOrder {
    private int id;
    private double quantity;
    private double price;
    private String symbol;
}
