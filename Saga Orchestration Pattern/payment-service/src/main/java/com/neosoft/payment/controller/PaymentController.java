package com.neosoft.payment.controller;

import com.neosoft.payment.dto.PaymentRequestDTO;
import com.neosoft.payment.dto.PaymentResponseDTO;
import com.neosoft.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/debit")
    public PaymentResponseDTO debit(@RequestBody PaymentRequestDTO paymentRequestDTO){
        return paymentService.debit(paymentRequestDTO);
    }

    @PostMapping("/credit")
    public void credit(@RequestBody PaymentRequestDTO paymentRequestDTO){
        paymentService.credit(paymentRequestDTO);
    }
}
