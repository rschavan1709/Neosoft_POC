package com.neosoft.payment.controller;

import com.neosoft.payment.dto.OrderRequestDTO;
import com.neosoft.payment.service.PaymentService;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create-order")
    public ResponseEntity createOrder(@RequestBody OrderRequestDTO orderRequestDTO) throws RazorpayException {
        return ResponseEntity.ok(paymentService.createOrder(orderRequestDTO));
    }
}
