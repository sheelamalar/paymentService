package com.reservation.paymentService.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class DirectExchangeConfig {
	Logger logger = LoggerFactory.getLogger(DirectExchangeConfig.class);
	
	@Bean
	Queue inventoryQueue() {
		logger.debug("inventoryQueue");
		
		//false: non-durable
		return new Queue("inventoryQueue", false);		
	}
	
	@Bean
	DirectExchange inventoryDirectExchange() {
		logger.debug("inventoryDirectExchange");
		
		return new DirectExchange("inventoryDirectExchange");
	}
	
	@Bean
	Binding inventoryDirectBinding(Queue inventoryQueue, DirectExchange inventoryDirectExchange) {
		logger.debug("inventoryDirectBinding");
		
		return BindingBuilder.bind(inventoryQueue).to(inventoryDirectExchange).with("queue.inventoryQueue");
	}
	

}
