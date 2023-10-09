package com.neosoft.buy.controller;

import com.neosoft.buy.model.BuyOrder;
import com.neosoft.buy.producer.BuyOrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class BuyOrderController {
    @Autowired
    private BuyOrderProducer buyOrderProducer;

    @PostMapping("/buy")
    public ResponseEntity<String> sendMessage(@RequestBody BuyOrder buyOrder){
        buyOrderProducer.sendMessage(buyOrder);
        return ResponseEntity.ok("Buy Order sent to RabbitMQ..");
    }
}
