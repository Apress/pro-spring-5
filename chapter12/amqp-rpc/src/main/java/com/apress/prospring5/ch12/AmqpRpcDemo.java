package com.apress.prospring5.ch12;

import com.apress.prospring5.ch12.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class AmqpRpcDemo {

	public static void main(String... args) throws Exception {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(RabbitMQConfig.class);
		RabbitTemplate rabbitTemplate = ctx.getBean(RabbitTemplate.class);
		rabbitTemplate.convertAndSend("FL");
		rabbitTemplate.convertAndSend("MA");
		rabbitTemplate.convertAndSend("CA");

		System.in.read();
		ctx.close();
	}
}
