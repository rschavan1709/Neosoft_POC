package com.neosoft.inventory.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neosoft.inventory.entity.Inventory;
import com.neosoft.inventory.event.InventoryEvent;
import com.neosoft.inventory.event.PaymentEvent;
import com.neosoft.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ReverseInventory {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    @KafkaListener(topics = "reversed-inventory",groupId = "inventoryGroup")
    public void reverseInventory(String event){
        try{
            InventoryEvent inventoryEvent=new ObjectMapper().readValue(event, InventoryEvent.class);
            Iterable<Inventory> inventories=inventoryRepository.findByItem(inventoryEvent.getOrder().getItem());
            inventories.forEach(i -> {
                i.setQuantity(i.getQuantity()+inventoryEvent.getOrder().getQuantity());
                inventoryRepository.save(i);
            });
            PaymentEvent paymentEvent=new PaymentEvent();
            paymentEvent.setOrder(inventoryEvent.getOrder());
            paymentEvent.setType("PAYMENT_REVERSED");
            kafkaTemplate.send("reversed-payments",paymentEvent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
