package com.neosoft.order.controller;

import com.neosoft.order.dto.OrderRequestDTO;
import com.neosoft.order.dto.OrderResponseDTO;
import com.neosoft.order.entity.Orders;
import com.neosoft.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public Mono<Orders> createOrder(@RequestBody Mono<OrderRequestDTO> orderMono){
        return orderMono.flatMap(orderService::createOrder);
    }

    @GetMapping("/all")
    public Flux<OrderResponseDTO> getOrders(){
        return orderService.getAllOrder();
    }
}
