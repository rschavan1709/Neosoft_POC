package com.neosoft.payment.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class PaymentRequestDTO {

    private Integer userId;
    private UUID orderId;
    private double amount;

}
