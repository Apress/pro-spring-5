package com.apress.prospring5.ch12.config;

import com.apress.prospring5.ch12.WeatherService;
import com.apress.prospring5.ch12.WeatherServiceImpl;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.remoting.client.AmqpProxyFactoryBean;
import org.springframework.amqp.remoting.service.AmqpInvokerServiceExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by iuliana.cosmina on 6/18/17.
 */
@Configuration
@ComponentScan("com.apress.prospring5.ch12")
@EnableRabbit
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

	@Bean Queue forecasts() {
		return new Queue(queueName, true);
	}

	@Bean Binding dataBinding(DirectExchange directExchange, Queue queue) {
		return BindingBuilder.bind(queue).to(directExchange).with(queueName);
	}

	@Bean RabbitAdmin admin() {
		RabbitAdmin admin =  new RabbitAdmin(connectionFactory());
		admin.declareQueue(forecasts());
		admin.declareBinding(dataBinding(weather(), forecasts()));
		return admin;
	}

	@Bean DirectExchange weather() {
		return new DirectExchange(exchangeName, true, false);
	}

	@Bean WeatherService weatherServiceProxy() throws Exception {
		AmqpProxyFactoryBean fact = new AmqpProxyFactoryBean();
		fact.setAmqpTemplate(amqpTemplate());
		fact.setServiceInterface(WeatherService.class);
		fact.afterPropertiesSet();
		return (WeatherService)fact.getObject();
	}

	@Bean AmqpInvokerServiceExporter weatherServiceExporter(){
		AmqpInvokerServiceExporter exporter = new AmqpInvokerServiceExporter();
		exporter.setServiceInterface(WeatherService.class);
		exporter.setAmqpTemplate(amqpTemplate());
		exporter.setService(new WeatherServiceImpl());
		return exporter;
	}

	@Bean
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		factory.setMaxConcurrentConsumers(5);
		return factory;
	}

}
