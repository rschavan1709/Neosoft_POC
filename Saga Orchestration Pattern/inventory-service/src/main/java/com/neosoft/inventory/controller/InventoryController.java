package com.neosoft.inventory.controller;

import com.neosoft.inventory.dto.InventoryRequestDTO;
import com.neosoft.inventory.dto.InventoryResponseDTO;
import com.neosoft.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/deduct")
    public InventoryResponseDTO deduct(@RequestBody InventoryRequestDTO inventoryRequestDTO){
        return inventoryService.deduct(inventoryRequestDTO);
    }

    @PostMapping("/add")
    public void add(@RequestBody InventoryRequestDTO inventoryRequestDTO){
        inventoryService.add(inventoryRequestDTO);
    }
}
