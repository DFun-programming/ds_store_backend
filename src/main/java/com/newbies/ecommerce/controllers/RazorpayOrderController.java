package com.newbies.ecommerce.controllers;

import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newbies.ecommerce.constants.RazorpayConstants;
import com.newbies.ecommerce.entities.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping("/order/v1")
public class RazorpayOrderController {

//	@Autowired
//	private RazorpayOrderService razorpayOrderService;
	
	@PostMapping("/create-order")
	Order createOrder(@RequestBody Order orders)
	{
		String orderId=null;
	    try {
	        RazorpayClient razorpay = new RazorpayClient(RazorpayConstants.razpay_key,RazorpayConstants.razpay_secret_key);
	        org.json.JSONObject orderRequest = new org.json.JSONObject();
	        
	        orderRequest.put("amount", orders.getAmount()*100); // amount in the smallest currency unit
	        orderRequest.put("currency", "INR");
//	        orderRequest.put("receipt", "order_rcptid_11");
	        

	        com.razorpay.Order order = razorpay.Orders.create(orderRequest);
	        orderId = order.get("id");
	        orders.setRazorpayOrderId(orderId);
	    } catch (RazorpayException e) {
	        // Handle Exception
	        System.out.println(e.getMessage());
	    }
	    return orders;
	}
}
