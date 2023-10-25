package com.neosoft.payment.event;

import com.neosoft.payment.dto.OrderRequest;
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
