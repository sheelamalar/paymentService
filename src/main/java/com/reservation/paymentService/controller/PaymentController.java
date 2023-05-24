package com.reservation.paymentService.controller;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reservation.paymentService.model.Payment;
import com.reservation.paymentService.repository.PaymentRepository;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
	Logger logger = LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	PaymentRepository adminRepository;

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	/*
	 * CRUD - Create
	 * URL: http://localhost:8113/api/payment/pay
	 */
	@PostMapping("/pay")
	public Payment pay(@RequestBody Payment payment) {
		Payment p = save(payment);
		
		return p;
	}
	
	public Payment save(Payment payment) {
		return adminRepository.save(payment);
	}
	
	public Payment pay(String message) {
		Payment p = new Payment();
		p.setBookingNumber(Integer.parseInt(message));
		p.setPaymentNumber(Integer.parseInt(message));
		p.setPaymentDate(new Date());
		
		Payment payment = save(p);
		if(payment != null) {
			//Register inventory queue
			String exchange = "inventoryDirectExchange";
			String routingKey = "queue.inventoryQueue";
			
			amqpTemplate.convertAndSend(exchange, routingKey, payment.getBookingNumber());
			logger.debug("Registered Inventory queue using direct exchange");
			
			return payment;
		}
		
		return null;
		
	}
	
	
}
