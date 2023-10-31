package com.neosoft.orderorchestrator.dto;

import com.neosoft.orderorchestrator.enums.OrderStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class OrderResponseDTO {
    private UUID orderId;
    private Integer userId;
    private Integer productId;
    private double amount;
    private OrderStatus status;
}
