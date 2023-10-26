package com.neosoft.payment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neosoft.payment.dto.OrderRequest;
import com.neosoft.payment.entity.Payment;
import com.neosoft.payment.event.OrderEvent;
import com.neosoft.payment.event.PaymentEvent;
import com.neosoft.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaOrderTemplate;

    @KafkaListener(topics = "new-orders",groupId = "orderGroup")
    public void processPayment(String event) throws JsonProcessingException {
        System.out.println("Received event"+event);
        OrderEvent orderEvent=new ObjectMapper().readValue(event,OrderEvent.class);
        OrderRequest order=orderEvent.getOrder();
        Payment payment=new Payment();
        try{
            payment.setAmount(order.getPrice());
            payment.setMode(order.getPaymentMode());
            payment.setOrderId(order.getOrderId());
            payment.setStatus("SUCCESS");
            paymentRepository.save(payment);

            PaymentEvent paymentEvent=new PaymentEvent();
            paymentEvent.setOrder(orderEvent.getOrder());
            paymentEvent.setType("PAYMENT_CREATED");
            kafkaTemplate.send("new-payments",paymentEvent);
        }catch (Exception e){
            payment.setOrderId(order.getOrderId());
            payment.setStatus("FAILED");
            paymentRepository.save(payment);

            OrderEvent oe=new OrderEvent();
            oe.setOrder(order);
            oe.setType("ORDER_REVERSED");
            kafkaOrderTemplate.send("reversed-orders",oe);
        }
    }
}
