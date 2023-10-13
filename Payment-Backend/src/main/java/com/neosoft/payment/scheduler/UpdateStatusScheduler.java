package com.neosoft.payment.scheduler;

import com.neosoft.payment.enums.OrderPaymentStatus;
import com.neosoft.payment.model.Entity.OrderPayment;
import com.neosoft.payment.repository.OrderPaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class UpdateStatusScheduler {

    @Value("${razorpay.key}")
    private String key;

    @Value("${razorpay.secret}")
    private String secret;

    @Autowired
    private OrderPaymentRepository orderPaymentRepository;

    @Scheduled(fixedRate = 30000)
    public void updateStatusSchedule() throws RazorpayException {

        RazorpayClient razorpay = new RazorpayClient(key, secret);
        List<OrderPayment> orderPaymentList =orderPaymentRepository.findAll();
        for (OrderPayment orderPayment:orderPaymentList){
            Optional<Order> order = Optional.ofNullable(razorpay.orders.fetch(orderPayment.getOrderId()));
            LocalDateTime currentDateTime=LocalDateTime.now();
            if (orderPayment.getStatus().equals(OrderPaymentStatus.CREATED) && currentDateTime.isAfter(orderPayment.getCreatedAt().plusMinutes(12)))
                orderPayment.setStatus(OrderPaymentStatus.CANCELLED);
            else {
                String status = order.get().get("status");
                orderPayment.setStatus(OrderPaymentStatus.valueOf(status.toUpperCase()));
            }
            orderPaymentRepository.save(orderPayment);
        }

    }
}
