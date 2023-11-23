package com.neosoft.payment.repository;

import com.neosoft.payment.entity.OrderPayment;
import com.neosoft.payment.enums.OrderPaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderPaymentRepository extends JpaRepository<OrderPayment,Integer> {
    OrderPayment findByPaymentId(String paymentId);
    OrderPayment findByOrderId(String orderId);

    List<OrderPayment> findByStatus(OrderPaymentStatus status);
}
