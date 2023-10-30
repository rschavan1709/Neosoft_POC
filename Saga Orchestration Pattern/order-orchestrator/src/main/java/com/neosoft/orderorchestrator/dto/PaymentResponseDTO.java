package com.neosoft.orderorchestrator.dto;

import com.neosoft.orderorchestrator.enums.PaymentStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class PaymentResponseDTO {

    private Integer userId;
    private UUID orderId;
    private double amount;
    private PaymentStatus status;

}
