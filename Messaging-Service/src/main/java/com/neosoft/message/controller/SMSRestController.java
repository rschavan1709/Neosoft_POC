package com.neosoft.message.controller;

import com.neosoft.message.dto.SMSSendRequest;
import com.neosoft.message.service.SMSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sms")
@Slf4j
public class SMSRestController {

    @Autowired
    private SMSService smsService;

    @PostMapping("/processSMS")
    public String processSMS(@RequestBody SMSSendRequest smsSendRequest){
        log.info("processSMS Started sendRequest "+smsSendRequest.toString());
        return smsService.sendSMS(smsSendRequest.getDestinationSMSNumber(),smsSendRequest.getSmsMessages());
    }
}
