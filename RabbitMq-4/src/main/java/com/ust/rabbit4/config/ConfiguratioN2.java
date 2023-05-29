package com.ust.rabbit4.config;

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
public class ConfiguratioN2 {
	/*public static final String QUEUE="ust_queue";
	public static final String EXCHANGE="ust_exchange";
	public static final String ROUTING_KEY="ust_routingKey";*/
	@Value("${ust.rabbitmq.ust_queue}")
	String queueName;
	
	@Value("${ust.rabbit.ust_exchange}")
	String exchangeName;
	
	@Value("${ust.rabbit.ust_routing_key}")
	String routingKey;
	@Bean
	public Queue queueo(){
		return new Queue(queueName);
	}
	@Bean
	public TopicExchange exchangeo() {
		return new TopicExchange(exchangeName);
	}
	@Bean
	public Binding bindingo(Queue queue,TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingKey);
	}
	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
	
	
	@Bean
	public AmqpTemplate templateo(ConnectionFactory connectionFactory) {//connectionfacotry connects between the producer and consumer
		final RabbitTemplate template=
		new RabbitTemplate(connectionFactory);
		template.setMessageConverter(converter());
		return template;
		
	}
}

	


