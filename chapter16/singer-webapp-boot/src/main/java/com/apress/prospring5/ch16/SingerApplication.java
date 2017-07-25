package com.apress.prospring5.ch16;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by iuliana.cosmina on 7/16/17.
 */
@SpringBootApplication
public class SingerApplication {

	private static Logger logger = LoggerFactory.getLogger(SingerApplication.class);

	public static void main(String... args) throws Exception {
		ConfigurableApplicationContext ctx = SpringApplication.run(SingerApplication.class, args);
		assert (ctx != null);
		logger.info("Application started...");

		System.in.read();
		ctx.close();
	}
}
