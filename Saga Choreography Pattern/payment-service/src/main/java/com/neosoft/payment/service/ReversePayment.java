package com.neosoft.payment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neosoft.payment.dto.OrderRequest;
import com.neosoft.payment.entity.Payment;
import com.neosoft.payment.event.OrderEvent;
import com.neosoft.payment.event.PaymentEvent;
import com.neosoft.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ReversePayment {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @KafkaListener(topics = "reversed-payments",groupId = "paymentGroup")
    public void reversePayment(String event){
        try{
            PaymentEvent paymentEvent=new ObjectMapper().readValue(event, PaymentEvent.class);
            OrderRequest order=paymentEvent.getOrder();
            Iterable<Payment> payments=paymentRepository.findByOrderId(order.getOrderId());
            payments.forEach(p -> {
                p.setStatus("FAILED");
                paymentRepository.save(p);
            });
            OrderEvent orderEvent=new OrderEvent();
            orderEvent.setOrder(paymentEvent.getOrder());
            orderEvent.setType("ORDER_REVERSED");
            kafkaTemplate.send("reversed-orders",orderEvent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
