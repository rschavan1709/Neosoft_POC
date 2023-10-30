package com.neosoft.order.service;

import com.neosoft.order.dto.OrchestratorResponseDTO;
import com.neosoft.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UpdateService {
    @Autowired
    private OrderRepository orderRepository;

    public Mono<Void> updateOrder(OrchestratorResponseDTO orchestratorResponseDTO){
        return orderRepository.findById(orchestratorResponseDTO.getOrderId())
                .doOnNext(p -> p.setStatus(orchestratorResponseDTO.getStatus()))
                .doOnNext(orderRepository::save)
                .then();
    }
}
