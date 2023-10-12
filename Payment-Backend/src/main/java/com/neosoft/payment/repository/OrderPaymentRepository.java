package com.neosoft.payment.repository;

import com.neosoft.payment.model.Entity.OrderPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPaymentRepository extends JpaRepository<OrderPayment,Integer> {
    OrderPayment findByPaymentId(String paymentId);
    OrderPayment findByOrderId(String orderId);
}
