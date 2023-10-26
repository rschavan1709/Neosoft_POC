package com.neosoft.inventory.event;

import com.neosoft.inventory.dto.OrderRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEvent {

    private String type;
    private OrderRequest order;
}
