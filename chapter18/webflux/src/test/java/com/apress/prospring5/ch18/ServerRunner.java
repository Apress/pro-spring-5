package com.apress.prospring5.ch18;

import com.apress.prospring5.ch18.web.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * Created by iuliana.cosmina on 8/6/17.
 */
public class ServerRunner {

	private static Logger logger = LoggerFactory.getLogger(ServerRunner.class);

	public static void main(String... args) throws Exception {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ServerConfig.class);
		Server server = ctx.getBean(Server.class);
		server.startTomcatServer();
		logger.info("Application started...");

		System.in.read();
		ctx.close();
	}
}
