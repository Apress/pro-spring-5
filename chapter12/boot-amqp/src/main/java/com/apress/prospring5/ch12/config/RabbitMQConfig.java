package com.apress.prospring5.ch12.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by iuliana.cosmina on 6/18/17.
 */
@Configuration
@ComponentScan("com.apress.prospring5.ch12")
public class RabbitMQConfig {
	final static String queueName = "forecasts";
	final static String exchangeName = "weather";



	@Bean CachingConnectionFactory connectionFactory() {
		return new CachingConnectionFactory("127.0.0.1");
	}

	@Bean RabbitTemplate amqpTemplate() {
		RabbitTemplate rabbitTemplate = new RabbitTemplate();
		rabbitTemplate.setConnectionFactory(connectionFactory());
		rabbitTemplate.setReplyTimeout(2000);
		rabbitTemplate.setRoutingKey(queueName);
		rabbitTemplate.setExchange(exchangeName);
		return rabbitTemplate;
	}

	@Bean RabbitAdmin admin() {
		return new RabbitAdmin(connectionFactory());
	}

	@Bean Queue forecasts() {
		return new Queue(queueName, true);
	}

	@Bean DirectExchange weather() {
		return new DirectExchange(exchangeName, true, false);
	}

	@Bean Binding dataBinding(DirectExchange directExchange, Queue queue) {
		return BindingBuilder.bind(queue).to(directExchange).with(queueName);
	}
}
