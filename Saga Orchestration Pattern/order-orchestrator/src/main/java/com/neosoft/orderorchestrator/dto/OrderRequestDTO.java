package com.neosoft.orderorchestrator.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderRequestDTO {
    private UUID orderId;
    private Integer userId;
    private Integer productId;
}
