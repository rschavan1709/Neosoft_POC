package com.neosoft.payment.controller;

import com.neosoft.payment.model.DTO.UpdateStatusRequestDTO;
import com.neosoft.payment.repository.OrderPaymentRepository;
import com.neosoft.payment.enums.OrderPaymentStatus;
import com.neosoft.payment.model.DTO.OrderRequestDTO;
import com.neosoft.payment.model.DTO.OrderResponseDTO;
import com.neosoft.payment.model.DTO.RefundRequestDTO;
import com.neosoft.payment.model.Entity.OrderPayment;
import com.razorpay.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {

    @Value("${razorpay.key}")
    private String key;

    @Value("${razorpay.secret}")
    private String secret;

    @Autowired
    private OrderPaymentRepository orderPaymentRepository;

    @PostMapping("/create-order")
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO paymentData) throws RazorpayException {
        RazorpayClient razorpay = new RazorpayClient(key,secret);

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("amount",paymentData.getAmount().multiply(BigDecimal.valueOf(100)));
        jsonObject.put("currency","INR");
        jsonObject.put("receipt","txn_12345");

        Order order = razorpay.orders.create(jsonObject);
        System.out.println(order);

        if (order == null){
            throw new RuntimeException("Order Not Created!!");
        }

        OrderPayment orderPayment=OrderPayment
                .builder().orderId(order.get("id"))
                .amount(paymentData.getAmount())
                .currency(order.get("currency"))
                .attempts(order.get("attempts"))
                .status(OrderPaymentStatus.CREATED).build();

        orderPaymentRepository.save(orderPayment);

        OrderResponseDTO orderResponseDTO =  OrderResponseDTO
                .builder().orderId(orderPayment.getOrderId())
                .currency(orderPayment.getCurrency())
                .amount(order.get("amount")).build();

        return ResponseEntity.ok(orderResponseDTO);
    }

    @PostMapping("/refund")
    public ResponseEntity<String> refundOrder(@RequestBody RefundRequestDTO refundRequestDTO) throws RazorpayException {
        RazorpayClient razorpay = new RazorpayClient(key,secret);

        OrderPayment orderPayment = orderPaymentRepository.findByPaymentId(refundRequestDTO.getPaymentId());
        if (orderPayment == null)
            throw new RuntimeException("Invalid payment id");

        JSONObject refundRequest = new JSONObject();
        refundRequest.put("speed","optimum");
        refundRequest.put("receipt","Receipt:"+refundRequestDTO.getPaymentId());

        Refund refund = razorpay.payments.refund(refundRequestDTO.getPaymentId(),refundRequest);
        if (refund == null)
            throw new RuntimeException("Payment Not Refunded");

        orderPayment.setStatus(OrderPaymentStatus.REFUNDED);
        orderPaymentRepository.save(orderPayment);

        return ResponseEntity.ok("Refund successfully done");
    }

    @PostMapping("/update-status")
    public ResponseEntity updateStatus(@RequestBody UpdateStatusRequestDTO updateStatusRequestDTO) throws RazorpayException {
       RazorpayClient razorpay=new RazorpayClient(key,secret);
       OrderPayment orderPayment= orderPaymentRepository.findByOrderId(updateStatusRequestDTO.getOrderId());
       Optional<Order> order = Optional.ofNullable(razorpay.orders.fetch(updateStatusRequestDTO.getOrderId()));
       if (orderPayment == null || order == null)
           throw new RuntimeException("OrderPayment Not found");
       orderPayment.setPaymentId(updateStatusRequestDTO.getPaymentId());
       orderPayment.setAttempts(order.get().get("attempts"));
       if (updateStatusRequestDTO.getStatus().equals(OrderPaymentStatus.PAID.toString())){
           orderPayment.setStatus(OrderPaymentStatus.PAID);
       }
       else if(updateStatusRequestDTO.getStatus().equals(OrderPaymentStatus.FAILED.toString())){
           orderPayment.setStatus(OrderPaymentStatus.FAILED);
       }
       else if(updateStatusRequestDTO.getStatus().equals(OrderPaymentStatus.CANCELLED.toString())){
           orderPayment.setStatus(OrderPaymentStatus.CANCELLED);
       }
       else {
           throw new RuntimeException("Status Not Valid");
       }
       orderPaymentRepository.save(orderPayment);
       return ResponseEntity.ok("Status Updated Successfully");
    }
}
