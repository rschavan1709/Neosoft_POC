package com.neosoft.payment.dto;

import com.neosoft.payment.enums.PaymentStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class PaymentResponseDTO {

    private Integer userId;
    private UUID orderId;
    private double amount;
    private PaymentStatus status;

}
