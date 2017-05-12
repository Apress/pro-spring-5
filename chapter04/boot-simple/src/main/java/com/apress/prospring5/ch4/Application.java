package com.apress.prospring5.ch4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

/**
 * Created by iuliana.cosmina on 3/19/17.
 */
@SpringBootApplication
public class Application {

	private static Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String... args) throws Exception {
		ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
		assert (ctx != null);
		logger.info("The beans you were looking for:");

		// listing all bean definition  names
		Arrays.stream(ctx.getBeanDefinitionNames()).forEach(logger::info);

		HelloWorld hw = ctx.getBean(HelloWorld.class);
		hw.sayHi();

		System.in.read();
		ctx.close();
	}
}
