package com.neosoft.bus.repository;

import com.neosoft.bus.entity.Bus;
import com.neosoft.bus.enums.BusStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BusRepository extends JpaRepository<Bus,Integer> {
    Bus findByBusId(UUID busId);
    Bus findByBusNo(int busNo);
    Bus findByBusName(String busName);

    Bus findByIdAndStatus(Integer busId, BusStatus status);
}
