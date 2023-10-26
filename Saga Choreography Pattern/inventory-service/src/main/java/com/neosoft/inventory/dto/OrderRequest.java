package com.neosoft.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private String item;
    private int quantity;
    private double price;
    private String paymentMode;
    private Integer orderId;
    private String address;
}
