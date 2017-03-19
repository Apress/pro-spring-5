package com.apress.prospring5.ch4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by iuliana.cosmina on 3/19/17.
 */
@Component
public class HelloWorld {

	private static Logger logger = LoggerFactory.getLogger(HelloWorld.class);

	public void sayHi() {
		logger.info("Hello World!");
	}
}
