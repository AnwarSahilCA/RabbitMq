package com.ust.rabbit4.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.ust.rabbit.model.OrderStatus;
import com.ust.rabbit.model.entity;

@Component
public class Consumer {
	@RabbitListener(queues="oqueue")
	public void message1(entity ent) {
		System.out.println("Message is"+ ent);
	}
	@RabbitListener(queues="ust_queue")
	public void message2(OrderStatus order) {
		System.out.println("message is"+ order);
	}
	

}
