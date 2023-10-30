package com.neosoft.order.controller;

import com.neosoft.order.dto.OrderRequestDTO;
import com.neosoft.order.dto.OrderResponseDTO;
import com.neosoft.order.entity.Order;
import com.neosoft.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public Mono<Order> createOrder(@RequestBody Mono<OrderRequestDTO> orderMono){
        return orderMono.flatMap(orderService::createOrder);
    }

    @PostMapping("/all")
    public Flux<OrderResponseDTO> getOrders(){
        return orderService.getAllOrder();
    }
}
