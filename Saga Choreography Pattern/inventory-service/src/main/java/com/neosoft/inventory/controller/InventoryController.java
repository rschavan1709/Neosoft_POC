package com.neosoft.inventory.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neosoft.inventory.dto.OrderRequest;
import com.neosoft.inventory.dto.StorageRequest;
import com.neosoft.inventory.entity.Inventory;
import com.neosoft.inventory.event.InventoryEvent;
import com.neosoft.inventory.event.PaymentEvent;
import com.neosoft.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private KafkaTemplate<String, InventoryEvent> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, PaymentEvent> kafkaPaymentTemplate;

    @KafkaListener(topics = "new-payments",groupId = "paymentGroup")
    public void updateInventory(String paymentEvent) throws JsonProcessingException {

        InventoryEvent event=new InventoryEvent();
        PaymentEvent p=new ObjectMapper().readValue(paymentEvent, PaymentEvent.class);
        OrderRequest order=p.getOrder();

        try{
            Iterable<Inventory> inventories=inventoryRepository.findByItem(order.getItem());
            boolean exists=inventories.iterator().hasNext();

            if (!exists)
                throw new Exception("Stock not available");

            inventories.forEach(
                    i -> {
                        i.setQuantity(i.getQuantity() - order.getQuantity());
                        inventoryRepository.save(i);
                    });

            event.setType("INVENTORY_UPDATED");
            event.setOrder(p.getOrder());
            kafkaTemplate.send("new-inventory",event);
        }catch (Exception e){
            PaymentEvent pe=new PaymentEvent();
            pe.setOrder(order);
            pe.setType("PAYMENT_REVERSED");
            kafkaPaymentTemplate.send("reversed-payments",pe);
        }
    }

    @PostMapping("/inventory")
    public void addInventory(@RequestBody StorageRequest storageRequest){
        Iterable<Inventory> items=inventoryRepository.findByItem(storageRequest.getItem());

        if (items.iterator().hasNext()){
            items.forEach(i -> {
                i.setQuantity(storageRequest.getQuantity()+i.getQuantity());
                inventoryRepository.save(i);
            });
        }else {
            Inventory i =new Inventory();
            i.setItem(storageRequest.getItem());
            i.setQuantity(storageRequest.getQuantity());
            inventoryRepository.save(i);
        }
    }
}
