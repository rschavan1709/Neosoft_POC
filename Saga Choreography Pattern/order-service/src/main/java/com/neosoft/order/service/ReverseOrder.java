package com.neosoft.order.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neosoft.order.entity.Order;
import com.neosoft.order.event.OrderEvent;
import com.neosoft.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReverseOrder {

    @Autowired
    private OrderRepository orderRepository;

    @KafkaListener(topics = "reversed-orders",groupId = "orderGroup")
    public void reverseOrder(String event){
        try{
            OrderEvent orderEvent=new ObjectMapper().readValue(event, OrderEvent.class);
            Optional<Order> order=orderRepository.findById(orderEvent.getOrder().getOrderId());
            order.ifPresent(o -> {
                o.setStatus("FAILED");
                orderRepository.save(o);
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
