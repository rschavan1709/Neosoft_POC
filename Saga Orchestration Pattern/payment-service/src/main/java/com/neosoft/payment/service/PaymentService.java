package com.neosoft.payment.service;

import com.neosoft.payment.dto.PaymentRequestDTO;
import com.neosoft.payment.dto.PaymentResponseDTO;
import com.neosoft.payment.enums.PaymentStatus;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    private Map<Integer,Double> paymentMap;

    @PostConstruct
    private void init(){
        paymentMap=new HashMap<>();
        paymentMap.put(1,500d);
        paymentMap.put(2,1000d);
        paymentMap.put(3,700d);
    }

    public PaymentResponseDTO debit(PaymentRequestDTO paymentRequestDTO){
        double balance = paymentMap.getOrDefault(paymentRequestDTO.getUserId(),0d);
        PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO();
        paymentResponseDTO.setOrderId(paymentRequestDTO.getOrderId());
        paymentResponseDTO.setUserId(paymentRequestDTO.getUserId());
        paymentResponseDTO.setAmount(paymentRequestDTO.getAmount());
        paymentResponseDTO.setStatus(PaymentStatus.PAYMENT_REJECTED);

        if (balance >= paymentRequestDTO.getAmount()){
            paymentResponseDTO.setStatus(PaymentStatus.PAYMENT_APPROVED);
            paymentMap.put(paymentRequestDTO.getUserId(),balance-paymentRequestDTO.getAmount());
        }
        return paymentResponseDTO;
    }

    public void credit(PaymentRequestDTO paymentRequestDTO){
        paymentMap.computeIfPresent(paymentRequestDTO.getUserId(),(k,v) -> v+paymentRequestDTO.getAmount());
    }
}
