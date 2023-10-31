package com.neosoft.order.repository;

import com.neosoft.order.entity.Orders;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends ReactiveCrudRepository<Orders, UUID> {

}
