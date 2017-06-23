package com.apress.prospring5.ch12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Created by iuliana.cosmina on 6/22/17.
 */
@SpringBootApplication
public class Application {
	private static Logger logger = LoggerFactory.getLogger(Application.class);

	final static String queueName = "forecasts";
	final static String exchangeName = "weather";

	@Bean Queue forecasts() {
		return new Queue(queueName, true);
	}

	@Bean DirectExchange weather() {
		return new DirectExchange(exchangeName, true, false);
	}

	@Bean Binding dataBinding(DirectExchange directExchange, Queue queue) {
		return BindingBuilder.bind(queue).to(directExchange).with(queueName);
	}

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		WeatherService weatherService = ctx.getBean(WeatherService.class);
		logger.info("Forecast for FL: " + weatherService.getForecast("FL"));
		logger.info("Forecast for MA: " + weatherService.getForecast("MA"));
		logger.info("Forecast for CA: " + weatherService.getForecast("CA"));

	}

}
