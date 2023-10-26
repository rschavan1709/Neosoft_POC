package com.neosoft.inventory.repository;

import com.neosoft.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Integer> {
    Iterable<Inventory> findByItem(String item);
}
