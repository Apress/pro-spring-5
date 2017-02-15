package com.apress.prospring5.ch3.annotated;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Created by iuliana.cosmina on 2/15/17.
 */
public class FieldInjection {

	public static void main(String... args) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/app-context.xml");
		ctx.refresh();

		Singer singerBean = ctx.getBean(Singer.class);
		singerBean.sing();

		ctx.close();
	}
}
