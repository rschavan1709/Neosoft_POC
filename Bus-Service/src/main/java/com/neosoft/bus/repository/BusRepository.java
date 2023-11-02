package com.neosoft.bus.repository;

import com.neosoft.bus.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BusRepository extends JpaRepository<Bus,Integer> {
    Bus findByBusId(UUID busId);
    Bus findByBusNo(Integer busNo);
    Bus findByBusName(String busName);
}
