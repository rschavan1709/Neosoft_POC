package com.neosoft.buy.producer;

import com.neosoft.buy.model.BuyOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class BuyOrderProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private static final Logger LOGGER= LoggerFactory.getLogger(BuyOrderProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(BuyOrder buyOrder){
        LOGGER.info(String.format("Message sent -> %s",buyOrder.toString()));
        rabbitTemplate.convertAndSend(exchange,routingKey,buyOrder);
    }
}
