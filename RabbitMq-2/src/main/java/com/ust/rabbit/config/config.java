package com.ust.rabbit.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {
	@Value("${order.queue}")
	String queue;
	@Value("${order.exchange}")
	String exchange;
	@Value("${order.routingkey}")
	String routing_key;
	@Bean
	public Queue queue() {
		return new Queue(queue);
	}
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchange);
	}
	@Bean
	public Binding binding(Queue queue,TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routing_key);
	}
	/*@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}*/
	@Bean
	public AmqpTemplate template(ConnectionFactory connectionfactory) {
		RabbitTemplate template=new RabbitTemplate(connectionfactory);
		template.setMessageConverter(new Jackson2JsonMessageConverter());
		return template;
		
	}

}
