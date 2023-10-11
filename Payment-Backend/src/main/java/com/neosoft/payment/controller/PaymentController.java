package com.neosoft.payment.controller;

import com.neosoft.payment.model.OrderRequestDTO;
import com.neosoft.payment.model.OrderResponseDTO;
import com.neosoft.payment.model.RefundRequestDTO;
import com.razorpay.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {

    @Value("${razorpay.key}")
    private String key;

    @Value("${razorpay.secret}")
    private String secret;

    @PostMapping("/create-order")
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO paymentData) throws RazorpayException {
        RazorpayClient razorpay = new RazorpayClient(key,secret);

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("amount",paymentData.getAmount().multiply(BigDecimal.valueOf(100)));
        jsonObject.put("currency","INR");
        jsonObject.put("receipt","txn_12345");

        Order order = razorpay.orders.create(jsonObject);
        System.out.println(order);

        OrderResponseDTO orderResponseDTO =  OrderResponseDTO
                .builder().orderId(order.get("id"))
                .currency("INR")
                .amount(order.get("amount")).build();

        System.out.println(orderResponseDTO);
        return ResponseEntity.ok(orderResponseDTO);
    }

    @PostMapping("/refund")
    public ResponseEntity<String> refundOrder(@RequestBody RefundRequestDTO refundRequestDTO) throws RazorpayException {
        RazorpayClient razorpay = new RazorpayClient(key,secret);

        JSONObject refundRequest = new JSONObject();
        refundRequest.put("speed","optimum");
        refundRequest.put("receipt","Receipt:"+refundRequestDTO.getPaymentId());

        Refund payment = razorpay.payments.refund(refundRequestDTO.getPaymentId(),refundRequest);
        return ResponseEntity.ok("Refund successfully done");
    }
}
