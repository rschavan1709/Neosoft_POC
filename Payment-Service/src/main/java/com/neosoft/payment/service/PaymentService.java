package com.neosoft.payment.service;

import com.neosoft.payment.dto.BaseResponse;
import com.neosoft.payment.dto.OrderRequestDTO;
import com.razorpay.RazorpayException;
import org.springframework.web.bind.annotation.RequestBody;

public interface PaymentService {

    BaseResponse createOrder(OrderRequestDTO orderRequestDTO) throws RazorpayException;

}
