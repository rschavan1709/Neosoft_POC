package com.neosoft.inventory.repository;

import com.neosoft.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Integer> {
    List<Inventory> findByItem(String item);
}
