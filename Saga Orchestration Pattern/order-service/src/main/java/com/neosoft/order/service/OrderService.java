package com.neosoft.order.service;

import com.neosoft.order.dto.OrchestratorRequestDTO;
import com.neosoft.order.dto.OrderRequestDTO;
import com.neosoft.order.dto.OrderResponseDTO;
import com.neosoft.order.entity.Order;
import com.neosoft.order.enums.OrderStatus;
import com.neosoft.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.util.Map;

@Service
public class OrderService {

    private static final Map<Integer,Double> ORDER_PRICE = Map.of(
            1,100d,
            2,200d,
            3,300d
    );

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Sinks.Many<OrchestratorRequestDTO> sink;

    public Mono<Order> createOrder(OrderRequestDTO orderRequestDTO){
         return orderRepository.save(dtoToEntity(orderRequestDTO))
                .doOnNext(e -> orderRequestDTO.setOrderId(e.getId()))
                .doOnNext(e -> emitEvent(orderRequestDTO));
    }

    public Flux<OrderResponseDTO> getAllOrder(){
        return orderRepository.findAll()
                .map(this::entityToDto);
    }

    private void emitEvent(OrderRequestDTO orderRequestDTO){
        sink.tryEmitNext(getOrchestratorRequestDTO(orderRequestDTO));
    }

    private OrchestratorRequestDTO getOrchestratorRequestDTO(OrderRequestDTO orderRequestDTO) {
        OrchestratorRequestDTO orchestratorRequestDTO=new OrchestratorRequestDTO();
        orchestratorRequestDTO.setUserId(orderRequestDTO.getUserId());
        orchestratorRequestDTO.setAmount(ORDER_PRICE.get(orderRequestDTO.getProductId()));
        orchestratorRequestDTO.setOrderId(orderRequestDTO.getOrderId());
        orchestratorRequestDTO.setProductId(orderRequestDTO.getProductId());
        return orchestratorRequestDTO;
    }

    private Order dtoToEntity(final OrderRequestDTO orderRequestDTO) {
        Order order=new Order();
        order.setUserId(orderRequestDTO.getUserId());
        order.setProductId(orderRequestDTO.getProductId());
        order.setPrice(ORDER_PRICE.get(order.getProductId()));
        order.setId(orderRequestDTO.getOrderId());
        order.setStatus(OrderStatus.ORDER_CREATED);
        return order;
    }

    private OrderResponseDTO entityToDto(Order order){
        OrderResponseDTO orderResponseDTO=new OrderResponseDTO();
        orderResponseDTO.setOrderId(order.getId());
        orderResponseDTO.setProductId(order.getProductId());
        orderResponseDTO.setUserId(order.getUserId());
        orderResponseDTO.setStatus(order.getStatus());
        orderResponseDTO.setAmount(order.getPrice());
        return orderResponseDTO;
    }
}
