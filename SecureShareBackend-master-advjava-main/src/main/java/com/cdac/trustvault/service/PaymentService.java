package com.cdac.trustvault.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class PaymentService {
//	@Value("${razorpay.keyId}")
//    private String razorpayKeyId;
//
//    @Value("${razorpay.keySecret}")
//    private String razorpayKeySecret;
//
//    public String createOrder(int amount) throws RazorpayException {
//        RazorpayClient razorpay = new RazorpayClient(razorpayKeyId, razorpayKeySecret);
//        
//        JSONObject orderRequest = new JSONObject();
//        orderRequest.put("amount", amount * 100);  // Amount in paise
//        orderRequest.put("currency", "INR");
//        orderRequest.put("receipt", "txn_123456");
//
//        Order order = razorpay.orders.create(orderRequest);
//        return order.toString();
//    }

}
