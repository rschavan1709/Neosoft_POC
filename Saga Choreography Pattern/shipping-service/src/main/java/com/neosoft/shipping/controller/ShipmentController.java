package com.neosoft.shipping.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neosoft.shipping.dto.OrderRequest;
import com.neosoft.shipping.entity.Shipment;
import com.neosoft.shipping.event.InventoryEvent;
import com.neosoft.shipping.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ShipmentController {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private KafkaTemplate<String, InventoryEvent> kafkaTemplate;

    @KafkaListener(topics = "new-inventory",groupId = "inventoryGroup")
    public void shippingOrder(String event) throws JsonProcessingException {
        Shipment shipment=new Shipment();
        InventoryEvent inventoryEvent=new ObjectMapper().readValue(event, InventoryEvent.class);
        OrderRequest order=inventoryEvent.getOrder();
        try{
            if (order.getAddress() == null){
                throw new Exception("Address not present");
            }
            shipment.setAddress(order.getAddress());
            shipment.setOrderId(order.getOrderId());
            shipment.setStatus("success");
            shipmentRepository.save(shipment);
        }catch (Exception e){
            shipment.setOrderId(order.getOrderId());
            shipment.setStatus("failed");
            shipmentRepository.save(shipment);

            InventoryEvent reverseEvent=new InventoryEvent();

            reverseEvent.setType("INVENTORY_REVERSED");
            reverseEvent.setOrder(order);
            kafkaTemplate.send("reversed-inventory",reverseEvent);
        }
    }

}
