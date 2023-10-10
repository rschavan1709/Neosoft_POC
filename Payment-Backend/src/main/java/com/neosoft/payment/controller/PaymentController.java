package com.neosoft.payment.controller;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {

    @Value("${razorpay.key}")
    private String key;

    @Value("${razorpay.secret}")
    private String secret;

    @PostMapping("/create-order")
    public String createOrder(@RequestBody Map<String,Object> data) throws RazorpayException {
        System.out.println(data);
        double amt = Double.parseDouble(data.get("amount").toString()) ;
        RazorpayClient razorpay = new RazorpayClient(key,secret);

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("amount",amt*100);
        jsonObject.put("currency","INR");
        jsonObject.put("receipt","txn_12345");

        Order order = razorpay.orders.create(jsonObject);
        System.out.println(order);
        return order.toString();
    }
}
