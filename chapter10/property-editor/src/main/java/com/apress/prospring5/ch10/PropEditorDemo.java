package com.apress.prospring5.ch10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

public class PropEditorDemo {

	private static Logger logger = LoggerFactory.getLogger(PropEditorDemo.class);

	public static void main(String... args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();

		ctx.load("classpath:spring/prop-editor-app-context.xml");
		ctx.refresh();
		Singer eric = ctx.getBean("eric", Singer.class);
		logger.info("Eric info: " + eric);
		Singer countrySinger = ctx.getBean("countrySinger", Singer.class);
		logger.info("John info: " + countrySinger);

		ctx.close();
	}
}
