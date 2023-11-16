package com.neosoft.user.producer;

import com.neosoft.user.dto.SMSSendRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class SMSProducer {

    private static final Logger LOGGER= LoggerFactory.getLogger(SMSProducer.class);

    private KafkaTemplate<String, SMSSendRequest> kafkaTemplate;

    public SMSProducer(KafkaTemplate<String, SMSSendRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(SMSSendRequest data){
        LOGGER.info(String.format("Message sent -> %s",data.toString()));
        Message<SMSSendRequest> message= MessageBuilder.withPayload(data).setHeader(KafkaHeaders.TOPIC,"sms-topic").build();
        kafkaTemplate.send(message);
    }

}
