package com.neosoft.order.event;

import com.neosoft.order.dto.OrderRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {

   private String type;
   private OrderRequest order;
}
