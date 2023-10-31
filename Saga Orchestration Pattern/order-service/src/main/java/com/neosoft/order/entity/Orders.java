package com.neosoft.order.entity;

import com.neosoft.order.enums.OrderStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import java.util.UUID;

@Data
public class Orders {
    @Id
    private UUID id;
    private Integer userId;
    private Integer productId;
    private double price;
    private OrderStatus status;

}
