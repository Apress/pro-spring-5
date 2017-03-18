package com.apress.prospring5.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Jsr330Demo {

	public static void main(String... args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/app-context-annotation.xml");
		ctx.refresh();

		MessageRenderer renderer = ctx.getBean("messageRenderer", MessageRenderer.class);
		renderer.render();

		ctx.close();
	}
}
