package com.neosoft.sell.producer;

import com.neosoft.sell.model.SellOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SellOrderProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private static final Logger LOGGER= LoggerFactory.getLogger(SellOrderProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(SellOrder sellOrder){
        LOGGER.info(String.format("Message sent -> %s",sellOrder.toString()));
        rabbitTemplate.convertAndSend(exchange,routingKey,sellOrder);
    }
}
