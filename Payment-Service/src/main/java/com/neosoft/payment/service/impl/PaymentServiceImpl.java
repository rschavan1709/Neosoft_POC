package com.neosoft.payment.service.impl;

import com.neosoft.payment.dto.BaseResponse;
import com.neosoft.payment.dto.OrderRequestDTO;
import com.neosoft.payment.dto.OrderResponseDTO;
import com.neosoft.payment.entity.OrderPayment;
import com.neosoft.payment.enums.OrderPaymentStatus;
import com.neosoft.payment.repository.OrderPaymentRepository;
import com.neosoft.payment.service.PaymentService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${razorpay.key}")
    private String key;

    @Value("${razorpay.secret}")
    private String secret;

    @Autowired
    private OrderPaymentRepository orderPaymentRepository;

    @Override
    public BaseResponse createOrder(OrderRequestDTO orderRequestDTO) throws RazorpayException {
        RazorpayClient razorpay = new RazorpayClient(key,secret);

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("amount",orderRequestDTO.getAmount().multiply(BigDecimal.valueOf(100)));
        jsonObject.put("currency","INR");
        jsonObject.put("receipt","txn_12345");

        Order order = razorpay.orders.create(jsonObject);
        System.out.println(order);

        if (order == null){
            throw new RuntimeException("Order Not Created!!");
        }

        OrderPayment orderPayment=OrderPayment
                .builder().orderId(order.get("id"))
                .amount(orderRequestDTO.getAmount())
                .ticketId(orderRequestDTO.getTicketId())
                .status(OrderPaymentStatus.CREATED).build();

        orderPayment=orderPaymentRepository.save(orderPayment);

        OrderResponseDTO orderResponseDTO =  OrderResponseDTO
                .builder().orderId(orderPayment.getOrderId())
                .ticketId(orderPayment.getTicketId())
                .amount(order.get("amount")).build();

        return BaseResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Order Created Successfully")
                .data(orderResponseDTO).build();
    }
}
