package com.neosoft.message.consumer;

import com.neosoft.message.dto.SMSSendRequest;
import com.neosoft.message.service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SMSConsumer {

    @Autowired
    private SMSService smsService;

    @KafkaListener(topics = "sms-topic",groupId = "myGroup")
    public void consumeSms(SMSSendRequest smsSendRequest){
        smsService.sendSMS(smsSendRequest.getDestinationSMSNumber(),smsSendRequest.getSmsMessages());
    }


}
