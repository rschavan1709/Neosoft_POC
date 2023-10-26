package com.neosoft.order.service;

import com.neosoft.order.dto.OrderRequest;
import com.neosoft.order.entity.Order;
import com.neosoft.order.event.OrderEvent;
import com.neosoft.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void createOrder(OrderRequest orderRequest) {
            Order order=new Order();
        try {
            order.setQuantity(orderRequest.getQuantity());
            order.setItem(orderRequest.getItem());
            order.setStatus("CREATED");
            order.setPrice(orderRequest.getPrice());
            order = orderRepository.save(order);
            orderRequest.setOrderId(order.getId());

            OrderEvent event = new OrderEvent();
            event.setOrder(orderRequest);
            event.setType("ORDER_CREATED");
            kafkaTemplate.send("new-orders", event);
        }catch (Exception e){
            order.setStatus("FAILED");
            orderRepository.save(order);
        }
    }

}
