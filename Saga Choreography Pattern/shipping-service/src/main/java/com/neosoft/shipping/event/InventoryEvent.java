package com.neosoft.shipping.event;

import com.neosoft.shipping.dto.OrderRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryEvent {
    private String type;
    private OrderRequest order;
}
