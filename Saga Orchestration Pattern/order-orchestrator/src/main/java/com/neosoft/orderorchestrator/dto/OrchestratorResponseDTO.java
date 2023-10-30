package com.neosoft.orderorchestrator.dto;

import com.neosoft.orderorchestrator.enums.OrderStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class OrchestratorResponseDTO {
    private Integer userId;
    private Integer productId;
    private UUID orderId;
    private double amount;
    private OrderStatus status;
}
