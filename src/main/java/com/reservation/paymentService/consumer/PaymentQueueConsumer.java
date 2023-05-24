package com.reservation.paymentService.consumer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.reservation.paymentService.controller.PaymentController;
import com.reservation.paymentService.model.Payment;

@Component
public class PaymentQueueConsumer {
	Logger logger = LoggerFactory.getLogger(PaymentQueueConsumer.class);

	@Autowired
	PaymentController paymentController;

	@RabbitListener(queues="paymentQueue",ackMode="MANUAL")
	public void receiveMessageFromPaymentQueue(String message, Channel channel,
			@Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
		logger.debug("receiveMessageFromPaymentQueue: "+message);
		
		try {
			Payment payment = paymentController.pay(message);
			
			if(payment != null) {
				logger.debug("Queue processed: Payment Added");
				channel.basicAck(tag, false);
			}
		} catch(Exception e) {
			logger.error(e.getLocalizedMessage());
		}
		
	}
}
