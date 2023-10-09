package com.neosoft.sell.controller;

import com.neosoft.sell.model.SellOrder;
import com.neosoft.sell.producer.SellOrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SellOrderController {
    @Autowired
    private SellOrderProducer sellOrderProducer;

    @PostMapping("/sell")
    public ResponseEntity<String> sendMessage(@RequestBody SellOrder sellOrder){
        sellOrderProducer.sendMessage(sellOrder);
        return ResponseEntity.ok("Sell order sent to RabbitMQ..");
    }
}
