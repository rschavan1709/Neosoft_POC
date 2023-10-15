package com.neosoft.payment.scheduler;

import com.neosoft.payment.enums.OrderPaymentStatus;
import com.neosoft.payment.model.Entity.OrderPayment;
import com.neosoft.payment.repository.OrderPaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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
        List<OrderPayment> orderPaymentList =orderPaymentRepository.findByStatus(OrderPaymentStatus.CREATED);
        for (OrderPayment orderPayment:orderPaymentList){
            Optional<Order> order = Optional.ofNullable(razorpay.orders.fetch(orderPayment.getOrderId()));
            Date date=order.get().get("created_at");
            LocalDateTime dateTime=date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            if (LocalDateTime.now().isAfter(dateTime.plusMinutes(12)))
                    orderPayment.setStatus(OrderPaymentStatus.CANCELLED);
            orderPaymentRepository.save(orderPayment);
        }

    }
}
