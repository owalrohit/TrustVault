package com.cdac.trustvault.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.trustvault.service.PaymentService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
@RestController
public class PaymentController {

	
	 @Autowired
	 private PaymentService paymentService;

	    @CrossOrigin
	    @PostMapping("/createOrder")
	    public ResponseEntity<?> createOrder(@RequestBody Map<String, Integer> requestData) {
	        try {
	            int amount = requestData.get("amount");
	            RazorpayClient razorpay = new RazorpayClient("rzp_test_u768hLGUYuYgrN", "NpMC5u6a7ZfjwnOoFBbMo5cN");

	            JSONObject orderRequest = new JSONObject();
	            orderRequest.put("amount", amount);
	            orderRequest.put("currency", "INR");
	            orderRequest.put("receipt", "txn_123456");

	            Order order = razorpay.orders.create(orderRequest);

	            Map<String, Object> response = new HashMap<>();
	            response.put("orderId", order.get("id")); // Razorpay Order ID

	            return ResponseEntity.ok(response);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating order: " + e.getMessage());
	        }
	    }
}
