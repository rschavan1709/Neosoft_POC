package com.neosoft.inventory.service;

import com.neosoft.inventory.dto.InventoryRequestDTO;
import com.neosoft.inventory.dto.InventoryResponseDTO;
import com.neosoft.inventory.enums.InventoryStatus;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InventoryService {

    private Map<Integer,Integer> inventoryMap;

    @PostConstruct
    private void init(){
        inventoryMap=new HashMap<>();
        inventoryMap.put(1,2);
        inventoryMap.put(2,3);
        inventoryMap.put(3,4);
    }

    public InventoryResponseDTO deduct(InventoryRequestDTO inventoryRequestDTO){
        int quantity = inventoryMap.getOrDefault(inventoryRequestDTO.getProductId(),0);
        InventoryResponseDTO inventoryResponseDTO = new InventoryResponseDTO();
        inventoryResponseDTO.setOrderId(inventoryRequestDTO.getOrderId());
        inventoryResponseDTO.setProductId(inventoryRequestDTO.getProductId());
        inventoryResponseDTO.setUserId(inventoryRequestDTO.getUserId());
        inventoryResponseDTO.setStatus(InventoryStatus.UNAVAILABLE);

        if (quantity > 0){
            inventoryResponseDTO.setStatus(InventoryStatus.AVAILABLE);
            inventoryMap.put(inventoryRequestDTO.getProductId(),quantity-1);
        }
        return inventoryResponseDTO;
    }

    public void add(InventoryRequestDTO inventoryRequestDTO){
        inventoryMap.computeIfPresent(inventoryRequestDTO.getProductId(),(k,v) -> v+1);
    }
}
